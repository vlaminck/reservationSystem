package reservationsystem

class AccountController {
  def accountService
  def personService
  def reserveService
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
              waitList: waitList
      ]
    }
    else {
      redirect(uri: '/')
    }
  }

  def create = {
    if (params.checkout) {
      flash.checkout = true
    }
    return [:]
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
    flash.message = message
    redirect(action: 'show')
  }

  def createAccount = {
    def redirectAction = 'create'
    def redirectParams = [message: 'Your passwords must match']
    if (params.password && params.password == params.confirmPassword) {
      def accountCreationMap = accountService.createAccount(params)
      def account = accountCreationMap.account
      def message = accountCreationMap.message
      if (account) {
        redirectAction = flash.checkout ? 'checkout' : 'show'
        redirectParams.message = message?.success
      }
      else {
        redirectAction = 'create'
        redirectParams.message = message?.error
      }
    }
    flash.message = redirectParams?.message
    redirect(action: redirectAction)
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

  def cart = {
    def mediaList = []
    if (!session.shoppingCart) {
      session.shoppingCart = []
    }
    session.shoppingCart.each { mediaId ->
      mediaList << Media.get(mediaId)
    }
    return [currentUser: Person.currentUser, mediaList: mediaList]
  }

  def checkout = {
    if (!session.shoppingCart) {
      session.shoppingCart = []
    }

    def account = Person.currentUser?.account
    def redirectAction = "cart"
    def message
    if (account) {
      def cart = []
      cart += session.shoppingCart
      cart.each { mediaId ->
        def media = Media.get(mediaId)
        message = reserveService.reserve(media, account)
        if (message.startsWith("Successfully")) {
          session.shoppingCart.remove(mediaId)
        }
        if (!message.startsWith("Successfully")) {
          flash.message = "There was an error reserving one or more of your items.\nPlease try again later."
        }
      }
      if (!flash.message) {
        redirectAction = "show"
      }
    }
    redirect(action: redirectAction)
  }

}
