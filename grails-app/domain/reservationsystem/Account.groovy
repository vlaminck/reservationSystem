package reservationsystem

class Account {

    Person owner
    String cardId
    ReservationList reservationList
    Boolean flagForDeletion = false
    Integer failedLoginAttempts = 0
    Date dateCreated
    Date lastUpdated

    static hasMany = [fines: Fine]

    static transients = ['isLocked']

    static constraints = {
        owner(nullable: false)
        cardId(nullable: true)
        reservationList(nullable: true)
    }

    Boolean hasReservationPrvlgs() {
        return true
    }

    def calculateFines() {
        Double total = 0.0
        fines.each {
            total += it.amountOwed
        }
        total = total.trunc(2)
        return total.toString()
    }

    def getIsLocked() {
        return failedLoginAttempts >= 5
    }

    def setIsLocked() {
        failedLoginAttempts = 5
    }

    def canReserveMedia() {
        return reservationList ? reservationList.canReserveMedia() : true
    }
}
