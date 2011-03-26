package reservationsystem

class Person {

  String firstName
  String lastName
  String email
  String phoneNumber
  UserLogin userLogin
  Account account

  static transients = ['currentUser']

  static belongsTo = [Account]

  static constraints = {
    firstName(nullable: false, blank: false)
    lastName(nullable: false, blank: false)
    email(nullable: false, blank: false, email:true)
    phoneNumber(nullable: false, blank: false)
    userLogin(nullable: false)
  }

  static def current = new ThreadLocal()

  static Person getCurrentUser() {
    current.get()
  }

  static void setCurrentUser(Person p) {
    current.set(p)
  }

  Boolean hasReserved(media){
    def reservations = account.reservationList?.reservations
    if(reservations){
      return reservations*.media.contains(media)
    }
    else {
      return false
    }
  }

}
