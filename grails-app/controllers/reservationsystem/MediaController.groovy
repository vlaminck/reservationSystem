package reservationsystem

class MediaController {

  def index = { }

  def show = {
    def media = Media.get(params.id)
    if (media) {
      def duplicates = Media.createCriteria().list {
        eq("title", media.title)
        eq("type", media.type)
        eq("isAvailable", true)
      }

      def availableFormats = duplicates*.format
      availableFormats = availableFormats.unique()
      return [media: media, availableFormats: availableFormats]
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
}
