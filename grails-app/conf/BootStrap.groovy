import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.GrailsApplication
import reservationsystem.*

class BootStrap {
  def springSecurityService

  def init = { servletContext ->

    if (GrailsApplication.ENV_DEVELOPMENT == GrailsUtil.getEnvironment()) {
      println ' --- --- --- '
      println " Environment: ${GrailsUtil.getEnvironment()} ... Running BootStrap "
      println ' --- --- --- '

      createUsers()

    }
    else {
      println ' --- --- --- '
      println " Environment: ${GrailsUtil.getEnvironment()} ... NOT Running BootStrap "
      println ' --- --- --- '
    }
  }
  def destroy = {
  }

  def createUsers() {

    def userRole = SecRole.findByAuthority('ROLE_USER') ?: saveDomain(new SecRole(authority: 'ROLE_USER'))
    def adminRole = SecRole.findByAuthority('ROLE_ADMIN') ?: saveDomain(new SecRole(authority: 'ROLE_ADMIN'))

    def adminUser = SecUser.findByUsername('admin') ?: saveDomain(new SecUser(
            username: 'admin',
            password: springSecurityService.encodePassword('admin'),
            enabled: true))

    def david = SecUser.findByUsername('david') ?: saveDomain(new SecUser(
            username: 'david',
            password: springSecurityService.encodePassword('pass'),
            enabled: true))

    def erik = SecUser.findByUsername('erik') ?: saveDomain(new SecUser(
            username: 'erik',
            password: springSecurityService.encodePassword('pass'),
            enabled: true))

    def laura = SecUser.findByUsername('laura') ?: saveDomain(new SecUser(
            username: 'laura',
            password: springSecurityService.encodePassword('pass'),
            enabled: true))

    def steve = SecUser.findByUsername('steve') ?: saveDomain(new SecUser(
            username: 'steve',
            password: springSecurityService.encodePassword('pass'),
            enabled: true))

    if (!adminUser.authorities.contains(adminRole)) {
      SecUserSecRole.create(adminUser, adminRole)
    }
    if (!david.authorities.contains(david)) {
      SecUserSecRole.create(david, userRole)
    }
    if (!erik.authorities.contains(erik)) {
      SecUserSecRole.create(erik, userRole)
    }
    if (!laura.authorities.contains(laura)) {
      SecUserSecRole.create(laura, userRole)
    }
    if (!steve.authorities.contains(steve)) {
      SecUserSecRole.create(steve, userRole)
    }

  }

  def saveDomain(domain) {
    if (!domain.save()) {
      println "### ERROR Creating ${domain.class.name} in ApplicationBootStrap ###"
      domain.errors.allErrors.each {
        println '\tError: ' + it
      }
    }
    return domain
  }

}

