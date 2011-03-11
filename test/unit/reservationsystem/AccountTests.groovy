package reservationsystem

import grails.test.*

class AccountTests extends GrailsUnitTestCase {

  def person
  def account

  protected void setUp() {
    super.setUp()

    mockDomain(Person)
    mockDomain(Account)
    mockDomain(UserLogin)
    mockDomain(Fine)

    person = TestFixtures.person()
    account = person.account
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testCalculateFines_noFines() {
    assertEquals "0.0", account.calculateFines()
  }

  void testCalculateFines_multipleFines() {
    def numberOfFines = 3
    def fines = []
    (1..numberOfFines).each {
      fines << TestFixtures.fine(account)
    }

    def assertValue = 2.49 * numberOfFines
    assertEquals assertValue.toString(), account.calculateFines()
  }

  private saveDomain() {
    if (!domain.save(flush: true)) {
      println "Domain failed to save! " + domain.errors.allErrors.toString()
      fail(domain.errors.allErrors.toString())
    }
    return domain
  }
}
