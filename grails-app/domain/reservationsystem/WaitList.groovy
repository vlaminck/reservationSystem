package reservationsystem

class WaitList {

    Media media
    Date dateCreated
    Date lastUpdated

    static belongsTo = [Media]

    static hasMany = [waitingPeople: WaitingPerson]

    static constraints = {
        media(nullable: false)
    }

    def getLastPosition() {
        return waitingPeople?.size()
    }
}
