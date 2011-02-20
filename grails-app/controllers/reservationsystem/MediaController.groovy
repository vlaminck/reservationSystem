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
}
