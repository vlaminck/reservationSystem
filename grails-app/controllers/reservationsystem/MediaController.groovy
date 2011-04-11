package reservationsystem

class MediaController {
  def reserveService

  def index = { }

  def show = {
    def message = params.message ?: null
    def media = Media.get(params.id)
    if (media) {
      def duplicates = Media.findAllByTitleAndType(media.title, media.type)
      return [media: media, duplicates: duplicates, message: message]
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

    // TODO: remove println's
    println currentUser
    println currentUser.canReserveMedia()
    println !currentUser.hasMaterialReserved(media)
    println (currentUser && currentUser.canReserveMedia() && !currentUser.hasMaterialReserved(media))
    if (currentUser && currentUser.canReserveMedia() && !currentUser.hasMaterialReserved(media)) {
      println media.isAvailable
      if (media.isAvailable) {
        reserveMessage = reserveService.reserve(media, Person.currentUser.account)
      }
      else {
        if (reserveService.addToWaitList(media, Person.currentUser.account)) {
          reserveMessage = "You have been added to the wait list."
        }
      }
    }
    redirect(action: 'show', id: media.id, params: ['message': reserveMessage])
  }

  def returnMedia = {
    def media = Media.get(params.id)
    def message = "ERROR!!!!"
    if (media) {
      message = reserveService.returnMedia(media, Person.currentUser.account)
    }
    redirect(controller: "account", action: "show", params: [message: message])
  }

  def stopWaiting = {
    def media = Media.get(params.id)
    def message = "ERROR!!!!"
    if (media) {
      message = reserveService.removeFromWaitList(media, Person.currentUser.account)
    }
    redirect(controller: "account", action: "show", params: [message: message])
  }
}
