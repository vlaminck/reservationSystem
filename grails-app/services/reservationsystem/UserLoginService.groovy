package reservationsystem

class UserLoginService {
  def springSecurityService

  static transactional = true

  def createLogin(params) {
    // create userLogin
    def userLogin = UserLogin.findByUsername(params?.email) ?: new UserLogin(
            username: params?.email,
            password: springSecurityService.encodePassword(params?.password),
            enabled: true)

    if (!userLogin.save()) {
      log.debug userLogin.errors
      userLogin = null
    }
    else {
      // give userLogin a role
      def role = SecRole.findByAuthority('ROLE_USER')
      if (!userLogin.authorities.contains(role)) {
        SecUserSecRole.create(userLogin, role)
      }
    }
    return userLogin
  }
}
