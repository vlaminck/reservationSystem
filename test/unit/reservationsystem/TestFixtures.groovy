package reservationsystem

class TestFixtures {

  static account = {
    return new Account(
            cardId: 123,
            flagGorDeletion: false
    )
  }

  static person = {
    def account = TestFixtures.account()
    def person = new Person(
            firstName: 'Foo',
            lastName: 'Bar',
            email: 'foo@bar.com',
            phoneNumber: 1234567890,
            userLogin: TestFixtures.userLogin(),
            account: account
    )
    account.owner = person
    saveDomain(person)
    saveDomain(account)
    return person
  }

  static userLogin = {
    def userLogin = new UserLogin(
            username: 'testUsername',
            password: 'testPassword',
            enabled: true,
            accountExpired: false,
            accountLocked: false,
            passwordExpired: false
    )
    saveDomain(userLogin)
    return userLogin
  }

  static fine = { account ->
    def fine = new Fine(
            amountOwed: 2.49,
            offense: 'whoCares',
            description: 'no one',
            accoount:account
    )
    account.addToFines(fine)
    saveDomain(fine)
    saveDomain(account)
    return fine
  }

  static saveDomain(domain) {
    if (!domain.save(flush: true)) {
      println "Domain failed to save! " + domain.errors.allErrors.toString()
      fail(domain.errors.allErrors.toString())
    }
    return domain
  }

}
