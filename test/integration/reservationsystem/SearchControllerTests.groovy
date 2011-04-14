package reservationsystem

import grails.test.*

class SearchControllerTests extends ControllerUnitTestCase {

  def controller
  def allTypes
  def allFormats

  protected void setUp() {
    super.setUp()

    IntegrationTestFixtures.createMedia()

    allTypes = ["PRINT", "AUDIO", "VIDEO"]
    allFormats = ["PAPER", "EPUB", "CD", "MP3", "DVD", "AVI"]

    controller = new SearchController()
    controller.params.type = allTypes
    controller.params.format = allFormats
    controller.params.query = "query"
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testResults_noQueryRedirectsHome() {
    controller.params.query = null
    assertNull controller.params.query
    assertNotNull controller.params.type
    assertNotNull controller.params.format

    controller.results()

    assertEquals "/", controller.redirectArgs.uri
  }

  void testResults_noTypeRedirectsHome() {
    controller.params.type = null
    assertNull controller.params.type
    assertNotNull controller.params.query
    assertNotNull controller.params.format

    controller.results()

    assertEquals "/", controller.redirectArgs.uri
  }

  void testResults_noformatRedirectsHome() {
    controller.params.format = null
    assertNotNull controller.params.type
    assertNotNull controller.params.query
    assertNull controller.params.format

    controller.results()

    assertEquals "/", controller.redirectArgs.uri
  }

  void testResults_noResultsReturnsMessage() {
    controller.params.query = "garbageString"

    def results = controller.results()

    assertNull results.print
    assertNull results.audio
    assertNull results.video
    assertEquals "Please refine your search", controller.flash.message
  }


  void testResults_printTypeReturnsOnlyPrintType() {
    controller.params.query = "as"
    controller.params.type = "PRINT"

    def results = controller.results()

    assertNotNull results.results.print
    assertNull results.results.audio
    assertNull results.results.video
    assertTrue results.results.print.size() > 0
    results.results.print.each{
      assertTrue (it.format == MediaFormat.PAPER || it.format == MediaFormat.EPUB)
    }
  }

  void testResults_audioTypeReturnsOnlyAudioType() {
    controller.params.query = "as"
    controller.params.type = "AUDIO"

    def results = controller.results()

    assertNull results.results.print
    assertNotNull results.results.audio
    assertNull results.results.video
    assertTrue results.results.audio.size() > 0
    results.results.print.each{
      assertTrue (it.format == MediaFormat.CD || it.format == MediaFormat.MP3)
    }
  }

  void testResults_videoTypeReturnsOnlyVideoType() {
    controller.params.query = "as"
    controller.params.type = "VIDEO"

    def results = controller.results()

    assertNull results.results.print
    assertNull results.results.audio
    assertNotNull results.results.video
    assertTrue results.results.video.size() > 0
    results.results.print.each{
      assertTrue (it.format == MediaFormat.DVD || it.format == MediaFormat.AVI)
    }
  }

  void testResults_paperFormatReturnsOnlyPaperFormat() {
    controller.params.query = "as"
    controller.params.format = "PAPER"

    def results = controller.results()

    assertNotNull results.results.print
    assertNull results.results.audio
    assertNull results.results.video
    assertTrue results.results.print.size() > 0
    results.results.print.each{
      assertEquals it.format, MediaFormat.PAPER
    }
  }

  void testResults_epubFormatReturnsOnlyEpubFormat() {
    controller.params.query = "as"
    controller.params.format = "EPUB"

    def results = controller.results()

    assertNotNull results.results.print
    assertNull results.results.audio
    assertNull results.results.video
    assertTrue results.results.print.size() > 0
    results.results.print.each{
      assertEquals it.format, MediaFormat.EPUB
    }
  }

  void testResults_cdFormatReturnsOnlyCdFormat() {
    controller.params.query = "as"
    controller.params.format = "CD"

    def results = controller.results()

    assertNull results.results.print
    assertNotNull results.results.audio
    assertNull results.results.video
    assertTrue results.results.audio.size() > 0
    results.results.print.each{
      assertEquals it.format, MediaFormat.CD
    }
  }

  void testResults_mp3FormatReturnsOnlyMp3Format() {
    controller.params.query = "as"
    controller.params.format = "MP3"

    def results = controller.results()

    assertNull results.results.print
    assertNotNull results.results.audio
    assertNull results.results.video
    assertTrue results.results.audio.size() > 0
    results.results.print.each{
      assertEquals it.format, MediaFormat.MP3
    }
  }

  void testResults_dvdFormatReturnsOnlyDvdFormat() {
    controller.params.query = "as"
    controller.params.format = "DVD"

    def results = controller.results()

    assertNull results.results.print
    assertNull results.results.audio
    assertNotNull results.results.video
    assertTrue results.results.video.size() > 0
    results.results.print.each{
      assertEquals it.format, MediaFormat.DVD
    }
  }

  void testResults_aviFormatReturnsOnlyAviFormat() {
    controller.params.query = "as"
    controller.params.format = "AVI"

    def results = controller.results()

    assertNull results.results.print
    assertNull results.results.audio
    assertNotNull results.results.video
    assertTrue results.results.video.size() > 0
    results.results.print.each{
      assertEquals it.format, MediaFormat.AVI
    }
  }

  def saveDomain(domain) {
    if (!domain.save()) {
      println "### ERROR Creating ${domain.class.name} in ApplicationBootStrap ###"
      domain.errors.allErrors.each {
        println '\tError: ' + it
      }
    }
    return domain
  }
}
