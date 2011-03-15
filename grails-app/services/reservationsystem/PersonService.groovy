package reservationsystem

class PersonService {

  static transactional = true

  def savePerson(person, params) {
    def ret
    if (person) {
      person.properties = params
      if (person.validate()) {
        if (person.save()) {
          //TODO: errorHandle
          ret = person
        }
      }
    }
    return ret
  }
}
