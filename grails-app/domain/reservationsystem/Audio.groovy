package reservationsystem

class Audio extends Media {

  String producer
  Long length

  static constraints = {
    producer(nullable: true, blank: true)
    length(nullable: true)
  }
}
