package reservationsystem

class Media {

  MediaType type
  MediaFormat format
  String artist
  String title
  String description
  Boolean isAvailable = true
  WaitList waitList

  static constraints = {
    artist(nullable: true, blank: false)
    type(nullable: false)
    format(nullable: false, blank: false)
    title(nullable: false, blank: false)
    description(nullable: true, blank: true)
    isAvailable(nullable: false)
    waitList(nullable: true)
  }

  def estimatedWait() {
    if(waitList){
      return "${waitList.getLastPosition()} weeks"
    }
    else {
      return ""
    }
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

