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
    def fines = Fine

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
    def account = Person.currentUser.account
    def message
    if (account) {
      message = accountService.flagForDeletion(account)
    }
    redirect(action: 'show', params: [message: message])
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

  def fineMe = {
    if (Person.currentUser) {
      def account = Person.currentUser.account
      def fine = new Fine(offense: "being lazy", description: "get off your ass once in a while", amountOwed: 2.49, account: account)
      account.addToFines(fine)
      account.save()
    }
    redirect(action: 'show')
  }

  def payFines = {
    return [:]
  }

}
