package reservationsystem

class ReserveService {

  static transactional = true

  def reserve(media, account) {
    def message
    if (account?.hasReservationPrvlgs()) {
      if (!account.reservationList) {
        createReservationList(account)
      }
      def reservation = new Reservation(media: media)
      account.reservationList.addToReservations(reservation)
      if (reservation.save()) {
        media.isAvailable = false
        media.save()
      }
      if (!account.save()) {
        message = "Failed to Reserve ${media.title}"
      }
    }
    else {
      message = "You dan't have permission to reserve."
    }
    return message ?: "Successfully Reserved ${media.title}"
  }

  def returnMedia(media, account) {
    def message
    def reservation = account.reservationList.reservations.find {it.media == media}
    account.reservationList.removeFromReservations(reservation)
    if (!reservation.delete()) {
      log.warn "Unable to delete reservation $reservation, ${reservation.errors}"
    }
    media.isAvailable = true
    media.save()
    account.reservationList.save()
    account.save(flush: true)

    return "Successfully returned ${media.title}"
  }

  def reserveFromWaitList(media) {
    def waitList = media.waitList
    def waitingPeople
    def waitingPerson
    if (!waitList) {
      waitingPeople = waitList.waitingPeople.sort { it.positionInLine }
      waitingPerson = waitingPeople[0]
    }

    if (reserve(media, waitList.account)) {
      deleteWaitingPerson(waitingPerson)
      updateWaitListPositions(waitList)
      return true
    }
    else {
      return false
    }
  }

  def addToWaitList(media, person) {
    if (!media.waitList) {
      media.waitList = new WaitList(media: media).save()
      if (!media.save()) {
        log.debug media.errors
      }
    }
    if (!person.isOnWaitList(media)) {
      def position = media.waitList?.getLastPosition() ?: 0
      def waitingPerson = new WaitingPerson(account: person.account, media: media, positionInLine: position, waitList: media.waitList)
      if (waitingPerson.save()) {
        media.waitList?.addToWaitingPeople(waitingPerson)
        if (!media.save()) {
          log.debug media.errors
        }
        return person.isOnWaitList(media)
      }
      else {
        log.debug waitingPerson.errors
        return false
      }
    }
    else {
      return false
    }
  }

  def removeFromWaitList(media, account) {
    if (media && media.waitList && account) {
      def waitingPerson = WaitingPerson.findByWaitListAndAccount(media.waitList, account)
      deleteWaitingPerson(waitingPerson)

      if (media.waitList.waitingPeople.size() > 0) {
        updateWaitListPositions(media.wiatList)
        if (reserveFromWaitList(media)) {
          return true
        }
        else {
          return false
        }
      }
      else {
        return true
      }
    }
  }

  def updateWaitListPositions(waitList) {
    def waitingPeople = waitList.waitingPeople.sort { it.positionInLine }
    def count = 0
    waitingPeople.each {
      it.positionInLine = count++
      it.save()
    }
  }

  def deleteWaitingPerson(waitingPerson) {
    def waitList = waitingPerson.waitList
    waitList.removeFromWaitingPeople(waitingPerson)
    if (waitingPerson.delete()) {
      waitList.save()
    }

  }

  private createReservationList(account) {
    account.reservationList = new ReservationList().save()
    account.save()
  }
}
