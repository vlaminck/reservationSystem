package reservationsystem

class ReservationList {

  static hasMany = [reservations: Reservation]

  static constraints = {
  }

  def canReserveMedia() {
    return reservations ? reservations.size() < 5 : true
  }
}
