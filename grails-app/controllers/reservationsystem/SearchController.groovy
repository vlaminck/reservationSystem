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
        if (media.title?.contains(term) || media.artist?.contains(term)) searchResults << media
      }
    }

    searchResults = searchResults.unique {a, b -> a.title <=> b.title}

//    params.type?.each {
//      def tempResults = Media.findAllByType(MediaType."${it}")
//      searchResults += tempResults*.title
//    }
//
//    searchResults.unique()
//    searchResults.each{
//      println it
//    }
//
//




    return [results: searchResults]
  }
}
