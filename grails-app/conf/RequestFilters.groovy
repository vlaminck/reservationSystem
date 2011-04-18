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
                  def userDetails = springSecurityService.principal

                  if (userDetails?.id) {
                    def userLogin = UserLogin.get(userDetails?.id)
                    if (userLogin) {
                      Person.currentUser = Person.findByUserLogin(userLogin)
                      if (Person.currentUser?.account?.failedLoginAttempts > 0) {
//                        Person.currentUser.account.failedLoginAttempts = 0
//                        Person.currentUser.account.save()
                      }
                    }
                    else {
                      log.warn("Found UserDetails: ${userDetails} with no userLogin for ID: ${userDetails?.id}")
                    }

                  }
                }
                return true
              }
            }
  }
}  