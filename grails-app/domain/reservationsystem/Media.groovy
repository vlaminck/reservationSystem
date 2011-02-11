package reservationsystem

class Media {

  MediaType type
  String format
  String title
  String description

  static constraints = {
    type(nullable: false)
    format(nullable: false, blank: false)
    title(nullable: false, blank: false)
    description(nullable: true, blank: true)
  }
}

enum MediaType {
  PRINT, VIDEO, AuDIO
}

