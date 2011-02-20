package reservationsystem

class SearchController {
  def searchService

  def index = {  }

  def results = {
    println "-------------------"
    println params
    println ""
    params.type?.each {
      println it
    }
    println ""
    params.format?.each {
      println it
    }
    println "-------------------"


    def types = []
    params.type?.each {
      types << MediaType."${it}"
    }

    def formats = []
    params.format?.each {
      formats << MediaFormat."${it}"
    }

    def searchResults = Media.createCriteria().list {
      'in'("type", types)
      'in'("format", formats)

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
