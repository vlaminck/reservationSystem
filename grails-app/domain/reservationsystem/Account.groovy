package reservationsystem

class Account {

  Person owner
  String cardId
  ReservationList reservationList
  Boolean flagForDeletion = false

  static hasMany = [fines: Fine]

  static constraints = {
    owner(nullable: false)
    cardId(nullable: false)
    reservationList(nullable: true)
  }

  def calculateFines() {
    def total = 0.0
    fines.each{
      total += it.amountOwed
    }
    return total
  }
}
