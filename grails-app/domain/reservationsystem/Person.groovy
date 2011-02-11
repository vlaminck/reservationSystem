package reservationsystem

class Person {

  String name
  security.UserLogin userLogin

  static belongsTo = [Account]

  static constraints = {
    name(nullable:false, blank: false)
    userLogin(nullable: false)
  }
}
