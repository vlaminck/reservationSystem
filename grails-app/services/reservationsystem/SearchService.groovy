package reservationsystem

class SearchService {

  static transactional = true

  def findPrint(params) {
    def results
    if (params.title && params.author) {
      results = Print.findAllByTitleAndAuthor()
    }
    else if (params.title) {
      results = Print.findAllByTitle()
    }
    else if (params.author) {
      results = Print.findAllByAuthor()
    }
    return results
  }

  def findAudio(params) {
    def results
    if (params.title && params.author) {
      results = Audio.findAllByTitleAndAuthor()
    }
    else if (params.title) {
      results = Audio.findAllByTitle()
    }
    else if (params.author) {
      results = Audio.findAllByAuthor()
    }
    return results

  }

  def findVideo(params) {
    def results
    if (params.title && params.author) {
      results = Video.findAllByTitleAndAuthor()
    }
    else if (params.title) {
      results = Video.findAllByTitle()
    }
    else if (params.author) {
      results = Video.findAllByAuthor()
    }
    return results
  }

  def findMedia(params){


  }
}
