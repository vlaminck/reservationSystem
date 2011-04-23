package reservationsystem

class Fine {

    Double amountOwed
    String offense
    String description
    Date dateCreated
    Date lastUpdated


    static belongsTo = [Account]

    static constraints = {
    }
}
