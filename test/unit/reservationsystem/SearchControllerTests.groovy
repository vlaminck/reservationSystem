package reservationsystem

import grails.test.*

class SearchControllerTests extends ControllerUnitTestCase {

  def controller
  def allTypes
  def allFormats

  protected void setUp() {
    super.setUp()

    mockDomain(Media)
    TestFixtures.createMedia()

    allTypes = ["PRINT", "AUDIO", "VIDEO"]
    allFormats = ["PAPER", "EPUB", "CD", "MP3", "DVD", "AVI"]

    controller = new SearchController()
    controller.params.type = allTypes
    controller.params.format = allFormats
    controller.params.query = ""
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testResults_() {

  }


















  void testResults_noParamsTypeRedirectsHome() {
    controller.params.type = null

    controller.results()

    assertEquals "/", controller.redirectArgs.uri
  }

  void testResults_noParamsFormatRedirectsHome() {
    controller.params.format = null

    controller.results()

    assertEquals "/", controller.redirectArgs.uri
  }

  void testResults_noParamsQueryRedirectsHome() {
    controller.params.query = null

    controller.results()

    assertEquals "/", controller.redirectArgs.uri
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
