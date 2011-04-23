package reservationsystem

class Reservation {

    Media media
    Date dateCreated
    Date lastUpdated

    static belongsTo = [ReservationList]

    static constraints = {
    }
}
