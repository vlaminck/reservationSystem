package reservationsystem

class Video extends Media {

  String artist
  String producer
  String director
  Long runTime

  static constraints = {
    artist(nullable: false, blank: false)
    producer(nullable: true, blank: true)
    director(nullable: true, blank: true)
    runTime(nullable: true)
  }
}
