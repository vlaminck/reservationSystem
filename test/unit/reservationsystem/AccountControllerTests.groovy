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
    mockDomain(WaitList)
    mockDomain(WaitingPerson)

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

  void testShow_noCurrentUserRedirectsHome() {
    Person.currentUser = null

    controller.show()

    assertEquals '/', controller.redirectArgs.uri
  }

  void testCreate_accountCreatedDuringCheckoutPassedTrueInFlashScope() {
    controller.params.checkout = true

    def retMap = controller.create()

    assertTrue controller.flash.checkout
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
    controller.update()

    assertEquals 'create', controller.redirectArgs.action
  }

  void testUpdate_returnsCurrentUser() {
    Person.currentUser = person

    def retMap = controller.update()

    assertEquals person, retMap.person
  }

  void testDelete_noCurrentUserRedirectsWithMessage() {
    controller.delete()

    assertEquals "Couldn't find your account", controller.flash.message
  }

  void testDelete_redirectsWithMessage() {
    Person.currentUser = person
    controller.accountService = [flagForDeletion: {acc ->
      return "this should be returned"
    }]

    controller.delete()

    assertEquals "this should be returned", controller.flash.message
  }

  void testCreateAccount_noPasswordRedirectsWithErrorMessage() {
    controller.createAccount()

    assertEquals 'Your passwords must match', controller.flash.message
    assertEquals 'create', controller.redirectArgs.action
  }

  void testCreateAccount_noPasswordConfirmationMatchRedirectsWithErrorMessage() {
    controller.params.password = 'pass'
    controller.params.confirmPassword = 'pas'

    controller.createAccount()

    assertEquals 'Your passwords must match', controller.flash.message
    assertEquals 'create', controller.redirectArgs.action    
  }

  void testCreateAccount_serviceReturnsAccountAndMessageRedirectsToShow() {
    controller.params.password = 'pass'
    controller.params.confirmPassword = 'pass'
    controller.accountService = [createAccount:{p->
      return [account:person.account, message:[success:'this should be returned']]
    }]

    controller.createAccount()

    assertEquals 'this should be returned', controller.flash.message
    assertEquals 'show', controller.redirectArgs.action
  }

  void testCreateAccount_serviceDoesNotReturnAccountAndMessageRedirectsToCreate() {
    controller.params.password = 'pass'
    controller.params.confirmPassword = 'pass'
    controller.accountService = [createAccount:{p->
      return [account:null, message:[error:'this should be returned']]
    }]

    controller.createAccount()

    assertEquals 'this should be returned', controller.flash.message
    assertEquals 'create', controller.redirectArgs.action
  }


}
