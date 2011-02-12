package reservationsystem

class Reservation {

  Media media

  static belongsTo = [ReservationList]

  static constraints = {
  }
}
