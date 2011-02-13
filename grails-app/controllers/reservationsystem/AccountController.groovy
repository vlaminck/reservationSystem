package reservationsystem

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class AccountController {
  def accountService
  def springSecurityService

  static allowedMethods = [save: 'POST', update: 'POST']

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
    println params
    def account = accountService.createAccount(params)
//    def userLogin = account?.owner?.userLogin
//    if(userLogin){
//      SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userLogin, userLogin.password, true));
//    }
//
//    println "Person.currentUser: ${Person.currentUser}"

    redirect(action: 'show')
  }

  def update = {

  }

  def delete = {

  }

}
