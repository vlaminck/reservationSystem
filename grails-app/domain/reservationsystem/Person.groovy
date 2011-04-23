package reservationsystem

class Person {

    String firstName
    String lastName
    String email
    String phoneNumber
    UserLogin userLogin
    Account account
    Date dateCreated
    Date lastUpdated

    static transients = ['currentUser', 'shortUsername']

    static belongsTo = [Account]

    static constraints = {
        firstName(nullable: true, blank: false)
        lastName(nullable: true, blank: false)
        email(nullable: false, blank: false, email: true)
        phoneNumber(nullable: true, blank: false)
        userLogin(nullable: false)
    }

    static def current = new ThreadLocal()

    static Person getCurrentUser() {
        current.get()
    }

    static void setCurrentUser(Person p) {
        current.set(p)
    }

    Boolean hasMaterialReserved(media) {
        def reservations = account.reservationList?.reservations
        if (reservations) {
            return reservations*.media.contains(media)
        }
        else {
            return false
        }
    }

    def canReserveMedia() {
        return account.canReserveMedia()
    }

    Boolean isOnWaitList(media) {
        def waitingPerson = WaitingPerson.findByAccountAndWaitList(account, media?.waitList)
        return waitingPerson ? true : false
    }

    def estimatedWait(media) {
        def waitingPerson = WaitingPerson.findByAccountAndWaitList(account, media?.waitList)
        return waitingPerson?.estimatedWait()
    }

    def getShortUsername() {
        if (firstName && lastName) {
            return "$firstName $lastName"
        }
        else if (firstName) {
            return "$firstName"
        }
        else {
            return "${email.substring(0, email.indexOf('@'))}"
        }
    }

}
