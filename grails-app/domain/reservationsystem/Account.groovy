package reservationsystem

class Account {

  Person owner
  String cardId
  ReservationList reservationList
  Boolean flagForDeletion = false

  static hasMany = [fine: Fine]

  static constraints = {
    owner(nullable: false)
    cardId(nullable: false)
    reservationList(nullable: true)
  }
}
