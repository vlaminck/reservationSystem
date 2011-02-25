import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import reservationsystem.Person

class LogoutController {

  /**
   * Index action. Redirects to the Spring security logout uri.
   */
  def index = {
    // TODO  put any pre-logout code here
    Person.currentUser = null
    redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
  }
}
