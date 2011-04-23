package reservationsystem

class Print extends Media {

    String publisher
    String isbn

    static constraints = {
        pulisher(nullable: true, blank: true)
        isbn(nullable: true, blank: true)
    }
}
