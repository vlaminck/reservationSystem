package reservationsystem

import grails.test.*

class PersonServiceTests extends GrailsUnitTestCase {

  def person
  def service

  protected void setUp() {
    super.setUp()

    mockDomain(Person)
    mockDomain(Account)
    mockDomain(UserLogin)

    person = TestFixtures.person()
    service = new PersonService()
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testSavePerson() {
    def params = [firstName: 'newFirstName', lastName: 'newLastName']
    assertFalse person.firstName == params.firstName
    assertFalse person.lastName == params.lastName

    def newPerson = service.savePerson(person, params)

    assertEquals person, newPerson
    assertTrue person.firstName == params.firstName
    assertTrue person.lastName == params.lastName
  }

  void testSavePerson_noPersonReturnsNull() {
    assertNull service.savePerson(null, null)
  }

  void xtestSavePerson_personSaveFailureReturnsNull() {
    def params = [firstName: null, lastName: null]
    assertFalse person.firstName == params.firstName
    assertFalse person.lastName == params.lastName

    assertNull service.savePerson(person, params)

    println person.firstName
    println person.lastName

    assertNotNull person.firstName
    assertNotNull person.lastName
  }
}
