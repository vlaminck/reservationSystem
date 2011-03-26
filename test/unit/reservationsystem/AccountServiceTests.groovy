package reservationsystem

import grails.test.*

class AccountServiceTests extends GrailsUnitTestCase {

  def service
  def params
  def person

  protected void setUp() {
    super.setUp()

    mockDomain(UserLogin)
    mockDomain(Account)
    mockDomain(Person)

    service = new AccountService()
    service.userLoginService = [createLogin:{params ->
      return TestFixtures.userLogin()
    }]
    params = [
            firstName:'aFirstName',
            lastName:'aLastName',
            phoneNumber: '1234567890',
            email: 'foo@bar.com',
            cardId:'11111'
    ]
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testCreateAccount_noUserLoginReturnsNullAccount() {
    service.userLoginService = [createLogin:{params ->
      return null
    }]

    def retMap = service.createAccount([:])

    assertNull retMap.account
    assertEquals "An unexpected error occured.\nPlease try again later.", retMap.message.error
  }

  void testCreateAccount_createsAccountAndPerson() {
    assertEquals 0, Account.findAll().size()
    assertEquals 0, Person.findAll().size()

    def retMap = service.createAccount(params)

    assertNotNull Account.findAll()
    assertNotNull Person.findAll()
    assertEquals 1, Account.findAll().size()
    assertEquals 1, Person.findAll().size()

    assertNotNull retMap
    assertNotNull retMap.account
    assertNotNull retMap.message
    assertNotNull retMap.message.success
    
    def person = Person.findByEmail(params.email)
    def account = Account.findByCardId(params.cardId)

    assertNotNull person
    assertNotNull account

    assertEquals account, retMap.account
    assertEquals person, retMap.account.owner
    assertEquals "New account created successfully", retMap.message.success
  }

  void testCreateAccount_accountSaveErrorReturnsNullAcountAndErrorMessage() {
    params.cardId = null

    def retMap = service.createAccount(params)

    assertNull retMap.account
    assertEquals "Unable to create new account", retMap.message.error
  }

  void testCreateAccount_personSaveErrorReturnsNullAcountAndErrorMessage() {
    params.firstName = null

    def retMap = service.createAccount(params)

    assertNull retMap.account
    assertEquals "Unable to create new user", retMap.message.error    
  }

  void testFlagForDeletion_noAccountReturnsErrorMessage() {
    def message = service.flagForDeletion(null)

    assertEquals "Unable to find your Account.\nPlease try again later.", message
  }

  void testFlagForDeletion_flagsAccountForDeletion() {
    person = TestFixtures.person()
    assertFalse person.account.flagForDeletion

    def message = service.flagForDeletion(person.account)

    assertEquals "Your Account has been flagged for deletion", message
    assertTrue person.account.flagForDeletion
  }

  void testFlagForDeletion_accountSaveErrorDoesNotflagAccountForDeletion() {
    person = TestFixtures.person()
    person.account.cardId = null // forces account save to fail
    assertFalse person.account.flagForDeletion

    def message = service.flagForDeletion(person.account)
    
    assertEquals "Unable to flag your account fo deletion\nPlease try again later.", message
    assertFalse person.account.flagForDeletion
  }

}
