package reservationsystem

class ReserveService {

  static transactional = true

  def reserve(media) {
    def account = Person.currentUser.account
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

  private createReservationList(account) {
    account.reservationList = new ReservationList().save()
    account.save()
  }
}
