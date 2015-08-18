package saisonnalite

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SaisonnaliteController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Saisonnalite.list(params), model:[saisonnaliteCount: Saisonnalite.count()]
    }

    def show(Saisonnalite saisonnalite) {
        respond saisonnalite
    }

    def create() {
        respond new Saisonnalite(params)
    }

    @Transactional
    def save(Saisonnalite saisonnalite) {
        if (saisonnalite == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (saisonnalite.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond saisonnalite.errors, view:'create'
            return
        }

        saisonnalite.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'saisonnalite.label', default: 'Saisonnalite'), saisonnalite.id])
                redirect saisonnalite
            }
            '*' { respond saisonnalite, [status: CREATED] }
        }
    }

    def edit(Saisonnalite saisonnalite) {
        respond saisonnalite
    }

    @Transactional
    def update(Saisonnalite saisonnalite) {
        if (saisonnalite == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (saisonnalite.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond saisonnalite.errors, view:'edit'
            return
        }

        saisonnalite.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'saisonnalite.label', default: 'Saisonnalite'), saisonnalite.id])
                redirect saisonnalite
            }
            '*'{ respond saisonnalite, [status: OK] }
        }
    }

    @Transactional
    def delete(Saisonnalite saisonnalite) {

        if (saisonnalite == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        saisonnalite.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'saisonnalite.label', default: 'Saisonnalite'), saisonnalite.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'saisonnalite.label', default: 'Saisonnalite'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
