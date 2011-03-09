package reservationsystem

class MediaController {
  def reserveService

  def index = { }

  def show = {
    def message = params.message ?: null
    def media = Media.get(params.id)
    if (media) {
      def duplicates = Media.createCriteria().list {
        eq("title", media.title)
        eq("type", media.type)
        eq("isAvailable", true)
      }

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
    if (media.isAvailable) {
      reserveMessage = reserveService.reserve(media)
    }
    redirect(action: 'show', id: media.id, params: ['message': reserveMessage])
  }
}
