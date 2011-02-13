import reservationsystem.*
import grails.plugins.*

class RequestFilters {
  def springSecurityService
  def authenticateService

  def filters = {

    all(controller: '*', action: '*')
            {
              before = {
                println "[${new Date()}] Request for ${controllerName}/${actionName} from RemoteUser: ${request.remoteUser}"

                if (springSecurityService.isLoggedIn()) {
                  def principal = springSecurityService.getPrincipal()

                  if (principal?.id) {
                    def userLogin = UserLogin.get(principal?.id)
                    if (userLogin) {
                      Person.currentUser = Person.findByUserLogin(userLogin)
                    }
                    else {
                      log.warn("Found principal: ${principal} with no userLogin for ID: ${principal?.id}")
                    }

                  }
                }
                return true
              }
            }
  }
}  