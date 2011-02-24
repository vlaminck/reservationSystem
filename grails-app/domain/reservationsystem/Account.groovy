package reservationsystem

class Account {

  Person owner
  String cardId

  static hasMany = [reservationList: ReservationList, fine: Fine]

  static constraints = {
    owner(nullable: false)
    cardId(nullable: false)
  }
}
