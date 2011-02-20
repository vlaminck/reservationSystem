package reservationsystem

class SearchController {
  def searchService

  def index = {  }

  def results = {
//    println "-------------------"
//    println params
//    println ""
//    params.type?.each {
//      println it
//    }
//    println ""
//    params.format?.each {
//      println it
//    }
//    println "-------------------"

    def searchTerms = params.query?.split(" ")
    println searchTerms

    def types = []
    params.type?.each {
      types << MediaType."${it}"
    }

    def formats = []
    params.format?.each {
      formats << MediaFormat."${it}"
    }

    def filteredResults = Media.createCriteria().list {
      'in'("type", types)
      'in'("format", formats)
    }

    def searchResults = []
    filteredResults.each { media ->
      searchTerms.each { term ->
        if (media.title?.toLowerCase()?.contains(term.toLowerCase()) || media.artist?.toLowerCase()?.contains(term.toLowerCase())) {
          searchResults << media
        }
      }
    }

    searchResults = searchResults.unique {a, b -> a.title <=> b.title}

    def printResults = []
    def audioResults = []
    def videoResults = []

    searchResults.each { media ->
      if (media.type == MediaType.PRINT) {
        printResults << media
      }
      else if (media.type == MediaType.AUDIO) {
        audioResults << media
      }
      else if (media.type == MediaType.VIDEO) {
        videoResults << media
      }
    }

    return [results: [print: printResults, audio: audioResults, video: videoResults]]
  }
}
