package reservationsystem

class Print extends Media {

  String author
  String pulisher
  String isbn

  static constraints = {
    author(nullable: false, blank: false)
    pulisher(nullable: true, blank: true)
    isbn(nullable: true, blank: true)
  }
}
