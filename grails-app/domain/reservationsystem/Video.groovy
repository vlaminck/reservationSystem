package reservationsystem

class Video extends Media {

  String artist
  String producer
  String director
  Long runTime

  static constraints = {
    artist(nullable: true, blank: false)
    producer(nullable: true, blank: true)
    director(nullable: true, blank: true)
    runTime(nullable: true)
  }
}
