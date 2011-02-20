package reservationsystem

class Video extends Media {

  String producer
  String director
  Long runTime

  static constraints = {
    producer(nullable: true, blank: true)
    director(nullable: true, blank: true)
    runTime(nullable: true)
  }
}
