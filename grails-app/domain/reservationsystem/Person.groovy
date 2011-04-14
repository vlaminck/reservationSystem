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

  Boolean hasMaterialReserved(media){
    def reservations = account.reservationList?.reservations
    if(reservations){
      return reservations*.media.contains(media)
    }
    else {
      return false
    }
  }

  def canReserveMedia(){
    return account.canReserveMedia()
  }

  Boolean isOnWaitList(media) {
    def waitingPerson = WaitingPerson.findByAccountAndWaitList(account, media?.waitList)
    println "waitingPerson: $waitingPerson"
    return waitingPerson ? true : false
  }

  def estimatedWait(media){
    def waitingPerson = WaitingPerson.findByAccountAndWaitList(account, media?.waitList)
    return waitingPerson?.estimatedWait()
  }

}
