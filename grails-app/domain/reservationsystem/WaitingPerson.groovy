package reservationsystem

class WaitingPerson {

  WaitList waitList
  Integer positionInLine
  Account account

  static belongsTo = [WaitList]

  static constraints = {
    waitList(nullable: false)
    account(nullable: false)
    positionInLine(nullable: true)
  }

  def estimatedWait() {
    if (!positionInLine) positionInLine = 0
    def offset = positionInLine + 1
    def plural = offset != 1 ? 's' : ''
    return "$offset week$plural"
  }
}
