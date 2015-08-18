package saisonnalite
import grails.rest.Resource

@Resource(formats=['html','json', 'xml'])
class Saisonnalite {

    Long ref_fiche_produit
    Long stock_securite
    Long stock_maxi
    Float valeur
    String classe
    Float cout_passation_commande
    Float cout_passation_stock

    static constraints = {
    }
}
