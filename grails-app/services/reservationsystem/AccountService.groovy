package reservationsystem

class AccountService {
  def springSecurityService
  def userLoginService
  def personService

  static transactional = true

  def createAccount(params) {
    def userLogin = userLoginService.createLogin(params)
    def account
    def user
    def message = [:]

    if (userLogin) {
      account = new Account(cardId: params.cardId)
      user = new Person(
              firstName: params?.firstName,
              lastName: params?.lastName,
              phoneNumber: params?.phoneNumber,
              email: params?.email,
              userLogin: userLogin,
              account: account
      )
      account.owner = user

      if (!account.save()) {
        println account.errors
        //TODO: better errorHandling
        message.error = 'Unable to create new account'
        account = null
      }

      if (!user.save()) {
        println user.errors
        // TODO: better error handling
        message.error = 'Unable to create new user'
        account = null
      }
      if(!message.error){
        message.success = 'New account created successfully'
      }
    }

    return [account:account, message:message]
  }

  def flagForDeletion(account){
    account.flagForDeletion = true
    if(!account.save()){
      return "Unable to flag your account fo deletion\nPlease try again later." 
    }
    else {
      return "Your Account has been flagged for deletion"
    }
  }
}
