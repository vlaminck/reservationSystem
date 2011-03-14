package reservationsystem

import grails.test.*

class AccountControllerTests extends ControllerUnitTestCase {

  def controller
  def person

  protected void setUp() {
    super.setUp()

    mockDomain(Person)
    mockDomain(Account)
    mockDomain(UserLogin)

    person = TestFixtures.person()
    controller = new AccountController()
  }

  protected void tearDown() {
    super.tearDown()

    Person.currentUser = null
    controller.params.remove('message')
  }

  void testIndex_redirectsToShowIfLoggedIn() {
    controller.springSecurityService = [isLoggedIn: {
      return true
    }]

    controller.index()

    assertEquals 'show', controller.redirectArgs.action
  }

  void testIndex_redirectsToCreateIfNotLoggedIn() {
    Person.currentUser = person

    controller.springSecurityService = [isLoggedIn: {
      return false
    }]

    controller.index()

    assertEquals 'create', controller.redirectArgs.action
  }

  void testShow_returnsMessageFromParams() {
    Person.currentUser = person
    controller.params.message = 'this string should be returned'

    def retMap = controller.show()

    assertEquals 'this string should be returned', retMap.message
  }

  void testShow_noCurrentUserRedirectsHome() {
    controller.show()

    assertEquals '/', controller.redirectArgs.uri
  }

  void testCreate_returnsMessageFromParams() {
    controller.params.message = 'this string should be returned'

    def retMap = controller.create()

    assertEquals 'this string should be returned', retMap.message    
  }

  void testSave_noCurrentUserRedirectsHome() {
    controller.save()

    assertEquals '/', controller.redirectArgs.uri
    
  }

  void testSave_personSaveFailureRedirectsHome() {
    Person.currentUser = person
    controller.personService = [savePerson:{person, params ->
      return false
    }]

    controller.save()

    assertEquals '/', controller.redirectArgs.uri
  }

  void testSave_personSaveSuccessRedirectsShow() {
    Person.currentUser = person
    controller.personService = [savePerson:{person, params ->
      return true
    }]

    controller.save()

    assertEquals 'show', controller.redirectArgs.action
  }

  void testUpdate_noCurrentUserRedirectsToCreate() {

  }

  void testUpdate_returnsCurrentUser() {

  }

  void testDelete_noCurrentUserRedirectsWithMessage() {

  }

  void testDelete_redirectsWithMessage() {

  }

  void testCreateAccount_noPasswordRedirectsWithErrorMessage() {

  }

  void testCreateAccount_noPasswordConfirmationMatchRedirectsWithErrorMessage() {

  }

  void testCreateAccount_serviceReturnsAccountAndMessageRedirectsToShow() {

  }

  void testCreateAccount_serviceDoesNotReturnAccountAndMessageRedirectsToCreate() {

  }


}
