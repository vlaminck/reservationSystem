package reservationsystem

class Audio extends Media {

  String artist
  String producer
  Long length

  static constraints = {
    artist(nullable: false, blank: false)
    producer(nullable: true, blank: true)
    length(nullable: true)
  }
}
