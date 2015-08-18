class UrlMappings {

    static mappings = {

        "/saisonnalites"(resources:"saisonnalite")
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
