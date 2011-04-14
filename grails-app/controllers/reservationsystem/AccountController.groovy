package reservationsystem

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
    if (currentUser) {
      def account = currentUser.account
      def waitLists = WaitingPerson.findAllByAccount(account)*.waitList
      def waitList = waitLists ? waitLists*.media : []

      return [
              currentUser: currentUser,
              account: account,
              waitList: waitList,
              message: params?.message
      ]
    }
    else {
      redirect(uri: '/')
    }
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
    else {
      redirect(uri: '/')
    }
  }

  def update = {
    def person = Person.currentUser
    if (person) {
      return [person: Person.currentUser]
    }
    else {
      redirect(action: 'create')
    }
  }

  def delete = {
    def account = Person.currentUser?.account
    def message = "Couldn't find your account"
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

  def unlock = {
    // TODO: allow Brad to enter his name to unlock Person.currentUser
  }

}
