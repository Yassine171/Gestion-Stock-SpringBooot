package gestion_stock.gestionStockapp.exception;

public enum ErrorCode {
    //TODO complete error codes

    //Gestion des articles
    ARTICLE_NOT_FOUND(1000),
    ARTICLE_NOT_VALIDE(1001),
    ARTICLE_ALREADY_IN_USE(1002),
    //Gestion des catégories
    CATEGORIE_NOT_FOUND(2000),
    CATEGORIE_NOT_VALIDE(2001),
    CATEGORIE_ALREADY_IN_USE(2002),

    //Gestion des Clients
    CLIENT_NOT_FOUND(3000),
    CLIENT_NOT_VALIDE(3001),
    CLIENT_ALREADY_IN_USE(3002),
    COMMANDE_CLIENT_NOT_FOUND(4000),
    COMMANDE_CLIENT_NOT_VALIDE(4001),
    COMMANDE_CLIENT_NOT_MODIFIABLE(4002),
    COMMANDE_CLIENT_ALREADY_IN_USE(4003),
    LIGNE_COMMANDE_CLIENT_NOT_FOUND(5000),

    //Gestion des fournisseur
    FOURNISSEUR_NOT_FOUND(6000),
    FOURNISSEUR_NOT_VALIDE(6001),
    FOURNISSEUR_ALREADY_IN_USE(6002),
    COMMANDE_FOURNISSEUR_NOT_FOUND(7000),
    COMMANDE_FOURNISSEUR_ALREADY_IN_USE(7002),
    LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND(8000),

    //Gestion des ventes
    VENTE_NOT_FOUND(9000),
    VENTE_NOT_VALIDE(9001),
    VENTE_ALREADY_IN_USE(9002),
    LIGNE_VENTE_NOT_FOUND(10000),

    //Gestion des mouvements
    MVT_NOT_FOUND(11000),
     MVT_NOT_VALIDE(11001),
    //Administration
    ENTREPRISE_NOT_FOUND(12000),
    ENTREPRISE_NOT_VALIDE(12001),
    UTILISATEUR_NOT_FOUND(13000),
    UTILISATEUR_NOT_VALIDE(13001),
    UTILISATEUR_ALREADY_EXISTS(130002),
    UTILISATEUR_CHANGE_PASSWORD_NOT_VALIDE(130003),

    ROLE_NOT_FOUND(14000),
    BAD_CREDENTIALS(15000),
    URL_PHOTO_INVALIDE(16000),
    CONTEXT_NOT_FOUND(17000);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
