import java.util.HashMap;

class Inventaire {
    private HashMap<Integer, Bateau> bateaux;

    Inventaire() {
        this.bateaux = new HashMap<>();
        this.setInventaire();
    }

    /**
     * Ajoute un nombre pré-défini de bateaux à l'inventaire du joueur
     */
    private void setInventaire() {
        this.ajouterBateau(new Bateau(5));
        this.ajouterBateau(new Bateau(4));
        this.ajouterBateau(new Bateau(3));
        this.ajouterBateau(new Bateau(3));
        this.ajouterBateau(new Bateau(2));
    }

    /**
     * Ajoute un bateau à l'inventaire du joueur
     * @param b nouveau bateau
     */
    private void ajouterBateau(Bateau b) {
        this.bateaux.put(b.getId(), b);
    }

    /**
     * Retire le bateau de l'inventaire du joueur
     * @param b bateau
     */
    void retirerBateau(Bateau b) {
        this.bateaux.remove(b.getId());
    }

    /**
     * Renvoie le bateau ciblé
     * @param id indice du bateau dans l'inventaire
     * @return bateau
     */
    Bateau getBateau(int id) {
        return this.bateaux.get(id);
    }

    /**
     * Récupère la liste de bateaux
     * @return bateaux
     */
    HashMap<Integer, Bateau> getBateaux() {
        return this.bateaux;
    }
}
