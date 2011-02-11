package reservationsystem

class Video extends Media {

  String Artist
  String Producer
  String Director
  Long runTime

  static constraints = {
    Artist(nullable: false, blank: false)
    Producer(nullable: true, blank: true)
    Director(nullable: true, blank: true)
    runTime(nullable: true)
  }
}
