package reservationsystem

class Fine {

  Double amountOwed
  String offense
  String description


  static belongsTo = [Account]

  static constraints = {
  }
}
