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

    return [currentUser: currentUser, account: account, message: params?.message]
  }

  def create = {
    return [message: params?.message]
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
    def redirectAction = 'create'
    def redirectParams = [message: 'Your passwords must match']
    if (params.password && params.password == params.confirmPassword) {
      def accountCreationMap = accountService.createAccount(params)
      def account = accountCreationMap.account
      def message = accountCreationMap.message
      if (account) {
        redirectAction = 'show'
        redirectParams.message = message?.success
      }
      else {
        redirectAction = 'create'
        redirectParams.message = message?.error
      }
    }
    redirect(action: redirectAction, params: redirectParams)
  }

}
