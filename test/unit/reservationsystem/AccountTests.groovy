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

    person = TestFixtures.person()
    account = person.account
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testCalculateFines_noFines() {
    assertEquals 0.0, account.calculateFines()
  }

  void testCalculateFines_multipleFines() {

  }
}
