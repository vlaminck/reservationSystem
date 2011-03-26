package reservationsystem

class ReserveService {

  static transactional = true

  def reserve(media, account) {
    def message
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
    def position = WaitList.getLastPosition(media) + 1
    def waitList = new WaitList(account: account, media: media, position: position).save()
    return waitList
  }

  private createReservationList(account) {
    account.reservationList = new ReservationList().save()
    account.save()
  }
}
