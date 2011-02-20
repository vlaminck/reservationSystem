package reservationsystem

class Person {

  String firstName
  String lastName
  String email
  UserLogin userLogin
  Account account

  static transients = ['currentUser']

  static belongsTo = [Account]

  static constraints = {
    firstName(nullable: false, blank: false)
    lastName(nullable: false, blank: false)
    email(nullable: false, blank: false, email:true)
    userLogin(nullable: false)
  }

  static def current = new ThreadLocal()

  static Person getCurrentUser() {
    current.get()
  }

  static void setCurrentUser(Person p) {
    current.set(p)
  }

}
