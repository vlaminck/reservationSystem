package reservationsystem

class AccountService {
  def springSecurityService
  def userLoginService
  def personService

  static transactional = true

  def createAccount(params) {
    def userLogin
    def account
    def user

    // create userLogin
    userLogin = UserLogin.findByUsername(params?.email) ?: new UserLogin(
            username: params?.email,
            password: springSecurityService.encodePassword(params?.password),
            enabled: true)

    if (!userLogin.save()) {
      println userLogin.errors
      userLogin = null
    }
    else {
      // give userLogin a role
      def role = SecRole.findByAuthority('ROLE_USER')
      if (!userLogin.authorities.contains(role)) {
        SecUserSecRole.create(userLogin, role)
      }
    }

    if (userLogin) {
      account = new Account()
      user = new Person(
              firstName: params?.firstName,
              lastName: params?.lastName,
              email: userLogin.username,
              userLogin: userLogin,
              account: account
      )
      account.owner = user

      if (!account.save()) {
        println account.errors
        account = null
      }

      if (!user.save()) {
        println user.errors
      }
    }

    return account
  }
}
