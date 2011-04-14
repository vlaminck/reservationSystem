package reservationsystem

class SearchController {
  def searchService

  def index = {  }

  def results = {
    if (params.type && params.format && params.query && params.query.length() > 1) {
      def message
      def results = [:]
      def searchTerms = params.query?.split(" ")

      def types = []
      if (params.type.toString().startsWith('[') && params.type.toString().endsWith(']')) {
        params.type.each {
          types << MediaType."${it}"
        }
      }
      else {
        types << MediaType."${params.type}"
      }

      def formats = []
      if (params.format.toString().startsWith('[') && params.format.toString().endsWith(']')) {
        params.format.each {
          formats << MediaFormat."${it}"
        }
      }
      else {
        formats << MediaFormat."${params.format}"
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
      searchResults.each { media ->
        if (media.type == MediaType.PRINT) {
          if (!results.print) results.print = []
          results.print << media
        }
        else if (media.type == MediaType.AUDIO) {
          if (!results.audio) results.audio = []
          results.audio << media
        }
        else if (media.type == MediaType.VIDEO) {
          if (!results.video) results.video = []
          results.video << media
        }
      }

      if (!results) {
        message = "Please refine your search"
      }

      flash.message = message
      return [results: results]
    }
    else {
      flash.message = "Please Refine your search"
      redirect(uri: "/")
    }
  }
}
