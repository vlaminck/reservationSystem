package reservationsystem

class ReservationList {

    Date dateCreated
    Date lastUpdated

    static hasMany = [reservations: Reservation]

    static constraints = {
    }

    def canReserveMedia() {
        return reservations ? reservations.size() < 5 : true
    }
}
