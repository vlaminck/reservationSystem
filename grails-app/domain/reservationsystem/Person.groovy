package reservationsystem

class Person {

  String name
  SecUser userLogin

  static belongsTo = [Account]

  static constraints = {
    name(nullable:false, blank: false)
    userLogin(nullable: false)
  }
}
