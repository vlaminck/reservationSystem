package reservationsystem

class MediaController {
  def reserveService

  def index = { }

  def show = {
    def media = Media.get(params.id)
    if (media) {
      def duplicates = Media.findAllByTitleAndType(media.title, media.type)
      return [media: media, duplicates: duplicates, currentUser: Person.currentUser]
    }
    else {
      redirect(uri: "/")
    }
  }

  def list = {
    def media = [:]
    if (params.type) {
      media."${params.type.toLowerCase()}" = Media.findAllByType(MediaType."${params.type}")
    }
    else {
      media.print = Media.findAllByType(MediaType.PRINT)
      media.audio = Media.findAllByType(MediaType.AUDIO)
      media.video = Media.findAllByType(MediaType.VIDEO)
    }

    media.each { k, v ->
      v.unique {a, b -> a.title <=> b.title}
    }

    return [mediaList: media]
  }

  def reserve = {
    def reserveMessage = 'Unable to reserve media'
    def media = Media.get(params.id)
    def currentUser = Person.currentUser

    if (currentUser && currentUser.canReserveMedia() && !currentUser.hasMaterialReserved(media)) {
      if (media.isAvailable) {
        reserveMessage = reserveService.reserve(media, Person.currentUser.account)
        if (reserveMessage.startsWith("Successfully") && session.shoppingCart?.contains(media.id.toString())) {
          session.shoppingCart.remove(media.id.toString())
        }
      }
      else {
        if (reserveService.addToWaitList(media, Person.currentUser)) {
          reserveMessage = "You have been added to the wait list."
        }
      }
    }
    flash.message = reserveMessage
    redirect(action: 'show', id: media.id)
  }

  def returnMedia = {
    def media = Media.get(params.id)
    def message = "ERROR!!!!"
    if (media) {
      message = reserveService.returnMedia(media, Person.currentUser.account)
    }
    flash.message = message
    redirect(controller: "account", action: "show")
  }

  def stopWaiting = {
    def media = Media.get(params.id)
    def message = "Unable to remove you from wait list.\nPlease try again later."
    if (media) {
      if (reserveService.removeFromWaitList(media, Person.currentUser.account)) {
        message = "Successfully removed from wait list."
      }
    }
    flash.message = message
    redirect(controller: "account", action: "show")
  }

  def addToShoppingCart = {
    def media = Media.get(params.id)

    if (!session.shoppingCart) {
      session.shoppingCart = [params.id]
      flash.message = "${media.title} \nhas been added to your cart"
    }
    else if (!session.shoppingCart.contains(params.id)) {
      session.shoppingCart << params.id
      flash.message = "${media.title} \nhas been added to your cart"
    }
    else {
      flash.message = "${media.title} \nis already in your cart."
    }
    session.shoppingCart.unique()

    redirect(action: "show", id: params.id)
  }

  def removeFromShoppingCart = {
    def media = Media.get(params.id)
    if (!session.shoppingCart) {
      session.shoppingCart = []
    }

    if (!session.shoppingCart.contains(params.id)) {
      flash.message = "${media.title} \nis not in your cart"
    }
    else {
      session.shoppingCart.remove(params.id)
      flash.message = "${media.title} \nhas been removed from your cart."
    }

    if (session.shoppingCart.size() > 0) {
      redirect(controller: "account", action: "cart")
    }
    redirect(action: "show", id: params.id)
  }

}
