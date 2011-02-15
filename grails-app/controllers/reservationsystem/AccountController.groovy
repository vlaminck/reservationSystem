package reservationsystem

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class AccountController {
  def accountService
  def personService
  def springSecurityService

  static allowedMethods = [save: 'POST', createAccount: 'POST']

  def index = {
    if (springSecurityService.isLoggedIn()) {
      redirect(action: 'show')
    }
    else {
      redirect(action: 'create')
    }
  }

  def show = {
    def currentUser = Person.currentUser
    def account = currentUser.account

    return [currentUser: currentUser, account: account]
  }

  def create = {
    return [:]
  }

  def save = {
    def person = Person.currentUser
    if (person) {
      if (personService.savePerson(person, params)) redirect(action: 'show')
      else redirect(uri: '/')
    }

  }

  def update = {
    def person = Person.currentUser
    println person
    if (person) {
      return [person: Person.currentUser]
    }
    else {
      redirect(action: 'create')
    }
  }

  def delete = {

  }

  def createAccount = {
    def account = accountService.createAccount(params)
// TODO: auto-login
//    def userLogin = account?.owner?.userLogin
//    if(userLogin){
//      SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userLogin, userLogin.password, true));
//    }
//
//    println "Person.currentUser: ${Person.currentUser}"

    if (account) redirect(action: 'show')
    else redirect(action: 'create')
  }

}
