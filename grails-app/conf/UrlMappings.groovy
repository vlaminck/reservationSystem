class UrlMappings {

  static mappings = {
    "/$controller/$action?/$id?" {
      constraints {
        // apply constraints here
      }
    }

    "/login/$action?"(controller: "login")
    "/logout/$action?"(controller: "logout")

    "/"(view: "/index")
    "405"(view: '/error')
    "500"(view: '/error')
  }
}
