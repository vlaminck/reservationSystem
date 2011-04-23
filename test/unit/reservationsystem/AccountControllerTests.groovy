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
    mockDomain(Media)
    mockDomain(Print)
    mockDomain(Audio)
    mockDomain(Video)
    TestFixtures.createMedia()
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
    controller.personService = [savePerson: {person, params ->
      return false
    }]

    controller.save()

    assertEquals '/', controller.redirectArgs.uri
  }

  void testSave_personSaveSuccessRedirectsShow() {
    Person.currentUser = person
    controller.personService = [savePerson: {person, params ->
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
    controller.accountService = [createAccount: {p ->
      return [account: person.account, message: [success: 'this should be returned']]
    }]

    controller.createAccount()

    assertEquals 'this should be returned', controller.flash.message
    assertEquals 'show', controller.redirectArgs.action
  }

  void testCreateAccount_serviceDoesNotReturnAccountAndMessageRedirectsToCreate() {
    controller.params.password = 'pass'
    controller.params.confirmPassword = 'pass'
    controller.accountService = [createAccount: {p ->
      return [account: null, message: [error: 'this should be returned']]
    }]

    controller.createAccount()

    assertEquals 'this should be returned', controller.flash.message
    assertEquals 'create', controller.redirectArgs.action
  }

  void testCart_returnsMediaList() {
    Person.currentUser = person
    def cartMedia = createAndFillCart()
    assertNotNull controller.session.shoppingCart
    assertEquals 3, controller.session.shoppingCart.size()

    println cartMedia

    def retVal = controller.cart()

    assertEquals person, retVal.currentUser
    assertNotNull cartMedia.print
    assertNotNull cartMedia.audio
    assertNotNull cartMedia.video
    assertNotNull retVal.mediaList
    assertEquals 3, retVal.mediaList.size()
    assertTrue retVal.mediaList.contains(cartMedia.print)
    assertTrue retVal.mediaList.contains(cartMedia.audio)
    assertTrue retVal.mediaList.contains(cartMedia.video)
  }

  void testCart_emptyCartReturnsEmptyMediaList() {
    Person.currentUser = person
    controller.session.shoppingCart = []

    def retVal = controller.cart()

    assertEquals person, retVal.currentUser
    def emptyList = []
    assertEquals emptyList, retVal.mediaList
  }

  void testCart_noCartCreatesEmptyCart() {
    Person.currentUser = person
    assertNull controller.session.shoppingCart

    def retVal = controller.cart()

    assertEquals person, retVal.currentUser
    def emptyList = []
    assertEquals emptyList, retVal.mediaList
  }

  void testCheckout_noCartCreatesEmptyCartAndRedirectsToCart() {
    Person.currentUser = person
    assertNull controller.session.shoppingCart

    controller.checkout()

    assertEquals "cart", controller.redirectArgs.action
    assertEquals "Your cart was empty", controller.flash.message
  }

  void testCheckout_noAccountRedirectsToCart() {
    def cartMedia = createAndFillCart()
    assertNotNull controller.session.shoppingCart
    assertEquals 3, controller.session.shoppingCart.size()
    assertNull Person.currentUser

    controller.checkout()

    assertEquals "cart", controller.redirectArgs.action
    assertEquals "You must log in", controller.flash.message
  }

  void testCheckout_successfulReservationEmptiesCartAndRedirectsToShow() {
    Person.currentUser = person
    def cartMedia = createAndFillCart()
    assertNotNull controller.session.shoppingCart
    assertEquals 3, controller.session.shoppingCart.size()
    controller.reserveService = [
            reserve: { media, account ->
              return "Successfully passed"
            }
    ]

    controller.checkout()

    assertEquals 0, controller.session.shoppingCart.size()
    assertEquals "show", controller.redirectArgs.action
  }

  void testCheckout_unSuccessfulReservationDoesNotRemoveItemFromCart() {
    Person.currentUser = person
    def cartMedia = createAndFillCart()
    assertNotNull controller.session.shoppingCart
    assertEquals 3, controller.session.shoppingCart.size()
    controller.reserveService = [
            reserve: { media, account ->
              if (media == cartMedia.print) {
                return "FAIL"
              }
              else {
                return "Successfully passed"
              }
            }
    ]

    controller.checkout()

    assertEquals 1, controller.session.shoppingCart.size()
    assertEquals cartMedia.print.id, controller.session.shoppingCart[0]
    assertEquals "There was an error reserving one or more of your items.\nPlease try again later.", controller.flash.message
  }

  private createAndFillCart() {
    controller.session.shoppingCart = []
    def print = saveDomain(new Media(title: "testMedia1", artist: "testPerson1", type: MediaType.PRINT, format: MediaFormat.PAPER, isAvailable: true))
    def audio = saveDomain(new Media(title: "testMedia2", artist: "testPerson2", type: MediaType.AUDIO, format: MediaFormat.CD, isAvailable: true))
    def video = saveDomain(new Media(title: "testMedia3", artist: "testPerson3", type: MediaType.VIDEO, format: MediaFormat.DVD, isAvailable: true))
    assertNotNull print
    assertNotNull audio
    assertNotNull video
    assertNotNull controller.session.shoppingCart
    controller.session.shoppingCart << print.id
    controller.session.shoppingCart << audio.id
    controller.session.shoppingCart << video.id


    println Print.get(print.id)
    println Media.get(print.id)

    println "print: $print"
    println "audio: $audio"
    println "video: $video"
    println controller.session.shoppingCart

    return [print: print, audio: audio, video: video]
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
