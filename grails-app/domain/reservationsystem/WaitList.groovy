package reservationsystem

class WaitList {

  Media media
  Account account
  Integer position

  static constraints = {
    media(nullable: false)
    account(nullable: false)
    position(nullable: false)
  }

  def estimatedWait() {
    def offset = position + 1
    def plural = offset != 1 ? 's' : ''
    return "$offset week$plural"
  }

  static getLastPosition(media) {
    def waitLists = WaitList.findAllByMedia(media)
    waitLists.sort { it.position }
    return waitLists.size() > 0 ? waitLists[-1] : null
  }
}
