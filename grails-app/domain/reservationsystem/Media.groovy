package reservationsystem

class Media {

  MediaType type
  String format
  String title
  String description
  Boolean isAvailable = true

  static constraints = {
    type(nullable: false)
    format(nullable: false, blank: false)
    title(nullable: false, blank: false)
    description(nullable: true, blank: true)
    isAvailable(nullable: false)
  }
}

enum MediaType {
  PRINT, VIDEO, AuDIO
}

