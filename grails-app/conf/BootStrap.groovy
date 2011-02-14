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
      createMedia()

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

    def adminUser = UserLogin.findByUsername('admin') ?: saveDomain(new UserLogin(
            username: 'admin',
            password: springSecurityService.encodePassword('admin'),
            enabled: true))
    if (!adminUser.authorities.contains(adminRole)) {
      SecUserSecRole.create(adminUser, adminRole)
    }
    def adminAccount = new Account()
    def adminPerson = new Person(
            firstName: 'admin',
            lastName: 'admin',
            email: 'foo@bar.com',
            userLogin: adminUser,
            account: adminAccount
    )
    adminAccount.owner = adminPerson
    saveDomain(adminAccount)
    saveDomain(adminPerson)

    def david = UserLogin.findByUsername('david') ?: saveDomain(new UserLogin(
            username: 'david',
            password: springSecurityService.encodePassword('pass'),
            enabled: true))
    if (!david.authorities.contains(david)) {
      SecUserSecRole.create(david, userRole)
    }
    def davidAccount = new Account()
    def davidPerson = new Person(
            firstName: 'David',
            lastName: 'Bauer',
            email: 'foo@bar.com',
            userLogin: adminUser,
            account: adminAccount
    )
    davidAccount.owner = davidPerson
    saveDomain(davidAccount)
    saveDomain(davidPerson)

    def erik = UserLogin.findByUsername('erik') ?: saveDomain(new UserLogin(
            username: 'erik',
            password: springSecurityService.encodePassword('pass'),
            enabled: true))
    if (!erik.authorities.contains(erik)) {
      SecUserSecRole.create(erik, userRole)
    }
    def erikAccount = new Account()
    def erikPerson = new Person(
            firstName: 'Erik',
            lastName: 'Knutson',
            email: 'foo@bar.com',
            userLogin: adminUser,
            account: adminAccount
    )
    erikAccount.owner = erikPerson
    saveDomain(erikAccount)
    saveDomain(erikPerson)

    def laura = UserLogin.findByUsername('laura') ?: saveDomain(new UserLogin(
            username: 'laura',
            password: springSecurityService.encodePassword('pass'),
            enabled: true))
    if (!laura.authorities.contains(laura)) {
      SecUserSecRole.create(laura, userRole)
    }
    def lauraAccount = new Account()
    def lauraPerson = new Person(
            firstName: 'Laura',
            lastName: 'Sweet',
            email: 'foo@bar.com',
            userLogin: laura,
            account: lauraAccount
    )
    lauraAccount.owner = lauraPerson
    saveDomain(lauraAccount)
    saveDomain(lauraPerson)

    def steve = UserLogin.findByUsername('steve') ?: saveDomain(new UserLogin(
            username: 'steve',
            password: springSecurityService.encodePassword('pass'),
            enabled: true))
    if (!steve.authorities.contains(steve)) {
      SecUserSecRole.create(steve, userRole)
    }
    def steveAccount = new Account()
    def stevePerson = new Person(
            firstName: 'Steve',
            lastName: 'Vlaminck',
            email: 'foo@bar.com',
            userLogin: steve,
            account: steveAccount
    )
    steveAccount.owner = stevePerson
    saveDomain(steveAccount)
    saveDomain(stevePerson)
  }

  def createMedia() {
// Needed for Media
//    MediaType type
//    MediaFormat format
//    String title
// Optional for Media
//    String description

// Needed for Print
//    String author
// Optional for Print
//    String description
//    String isbn

// Needed for Audio
//    String artist
// Optional for Audio
//    String producer
//    Long length

// Needed for Video
//    String artist
// Optional for Video
//    String producer
//    String director
//    Long runTime

    // TODO: Create Print
    saveDomain(new Print(
            type: MediaType.PRINT,
            format: MediaFormat.EPUB,
            title: "The Hitchhiker's Guide to the Galaxy",
            author: 'Douglas Adams'
    ))

    // TODO: Create Audio
    saveDomain(new Audio(
            type: MediaType.AUDIO,
            format: MediaFormat.MP3,
            title: "In Rainbows",
            artist: 'Radiohead'
    ))

    // TODO: Create Video
    saveDomain(new Video(
            type: MediaType.VIDEO,
            format: MediaFormat.AVI,
            title: "Arrested Development",
            artist: 'Mitchell Hurwitz'
    ))


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

