package reservationsystem

class Account {

  Person owner

  static hasMany = [reservationList: ReservationList, fine: Fine]

  static constraints = {
    owner(nullable: false)
  }
}
