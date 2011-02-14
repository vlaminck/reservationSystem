package reservationsystem

class Media {

  MediaType type
  MediaFormat format
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
  PRINT, VIDEO, AUDIO
}

enum MediaFormat {
// PRINT
  PAPER, EPUB,
// AUDIO
  CD, MP3,
// VIDEO
  DVD, AVI
}

