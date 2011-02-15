package reservationsystem

class PersonService {

    static transactional = true

  def savePerson(person, params){
    person.properties = params
    if(!person.save()){
      //TODO: errorHandle
      person = null
    }
    return person
  }
}
