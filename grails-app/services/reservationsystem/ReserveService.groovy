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
    return message ?: "Susccessfully Reserved ${media.title}"
  }

  def returnMedia(media, account) {
    def message
    println account
    println account.reservationList
    def reservation = account.reservationList.reservations.find {it.media == media}
    println reservation
    account.reservationList.removeFromReservations(reservation)
    if (!reservation.delete()) {
      log.warn "Unable to delete reservation $reservation, ${reservation.errors}"
    }
    media.isAvailable = true
    media.save()
    account.reservationList.save()
    account.save(flush: true)
    println account.reservationList.reservations*.media

    return "Successfully returned ${media.title}"
  }

  def reserveFromWaitList(media) {
    def waitList = WaitList.findByMediaAndPosition(media, 0)
    if (!waitList) {
      def waitLists = WaitList.findAllByMedia(media)
      waitLists.sort { it.position }
      waitList = waitLists[0]
    }
    if (reserve(media, waitList.account)) {
      waitList.delete()
      def waitLists = WaitList.findAllByMedia(media)
      if (waitLists.contains(waitList)) {
        waitLists.remove(waitList)
      }
      waitLists.sort { it.position }
      waitLists.eachWithIndex { wL, idx ->
        wL.position = idx
        wL.save()
      }
    }
  }

  def addToWaitList(media, account) {
    // TODO: remove println's
    println ""
    if (!media.waitList) {
      media.waitList = new WaitList(media: media)
      if(!media.save()){
        println media.errors
      }
    }
    println media.waitList
    println media.waitList?.waitingPeople
    def position = media.waitList?.getLastPosition() ?: 0
    def waitingPerson = new WaitingPerson(account: account, media: media, positionInLine: position, waitList:media.waitList)
    if (waitingPerson.save()) {
      media.waitList?.addToWaitingPeople(waitingPerson)
      if(!media.save()){
        println media.errors
      }
    }
    else {
      println waitingPerson.errors
    }
    println waitingPerson
    println media.waitList*.waitingPeople
    println media.waitList?.waitingPeople.find { it == waitingPerson }
    return media.waitList?.waitingPeople.find { it == waitingPerson }
  }

  def removeFromWaitList(media, account) {
    def waitList = WaitList.findByMediaAndAccount(media, account)
    waitList.delete()

    if (WaitList.countByMedia(media) > 0) {
      updateWaitListPositions(media)
      reserveFromWaitList(media)
    }
    else {
      media.isAvailable = true
    }
  }

  def updateWaitListPositions(media) {
    def waitLists = WaitList.findAllByMedia(media)
    waitLists.sort { it.position }
    def count = 0
    waitLists.each {
      it.position = count++
      it.save()
    }
  }

  private createReservationList(account) {
    account.reservationList = new ReservationList().save()
    account.save()
  }
}
