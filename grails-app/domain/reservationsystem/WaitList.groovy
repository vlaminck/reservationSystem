package reservationsystem

class WaitList {

  Media media

  static belongsTo = [Media]

  static hasMany = [waitingPeople: WaitingPerson]

  static constraints = {
    media(nullable: false)
  }

  def getLastPosition() {
    return waitingPeople?.size()
  }
}
