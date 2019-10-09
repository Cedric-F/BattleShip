class Joueur {

    private String pseudo;
    private Grille perso;
    private Grille attaque;
    private Inventaire inventaire;

    Joueur(String pseudo) {
        this.pseudo = pseudo;
        this.perso = new Grille(10);
        this.attaque = new Grille(10);
        this.inventaire = new Inventaire();
    }

    void setInventaire() {
        System.out.printf("Inventaire de %s initialisé.%n", this.pseudo);
        this.inventaire.ajouterBateau(new Bateau(0,5));
        this.inventaire.ajouterBateau(new Bateau(1,4));
        this.inventaire.ajouterBateau(new Bateau(2,3));
        this.inventaire.ajouterBateau(new Bateau(3,3));
        this.inventaire.ajouterBateau(new Bateau(4,2));
        System.out.printf("Inventaire de %s complet : %s bateaux.%n", this.pseudo, this.inventaire.getBateaux().size());
    }

    Inventaire getInventaire() {
        return this.inventaire;
    }

    private Grille getPerso() {
        return this.perso;
    }

    Grille getAttaque() {
        return this.attaque;
    }

    /**
     * Marque la case comme touchée sur la grille de l'attaquant (-1)
     * Vérifie la case ciblée sur la grille perso du joueur adverse.
     * Si un bateau occupe cette case (case == 1), celle-ci prend la valeur -1.
     * @param x abscisse du point d'attaque
     * @param y ordonnée du point d'attaque
     * @param cible Joueur ciblé
     * @return true si touché
     */
    boolean tirer(int x, int y, Joueur cible) {
        boolean touche;

        Grille a = this.getAttaque();
        int[][] g = a.getGrille();
        if (g[x][y] != -1) {

            g[x][y] = -1;
            this.attaque.updateGrille(g);

            Grille ciblePerso = cible.getPerso();
            int[][] grillePerso = cible.getPerso().getGrille();
            touche = ciblePerso.contenuCase(x, y) == 1;
            if (touche) {
                grillePerso[x][y] = -1;
                cible.perso.updateGrille(grillePerso);
            }
        } else {
            System.out.println("Vous avez déjà tiré à ces coordonnées!");
            touche = false;
        }

        return touche;
    }

    /**
     * Permet de placer un bateau sur la grille perso, en vérifiant si l'espace utilisé est libre et dans le plan
     * @param b Objet Bateau
     * @param x abscisse de départ
     * @param y ordonnée de départ
     * @param direction en ligne ou en colonne
     */
    void placer(Bateau b, int x, int y, String direction) {

        Grille g = this.getPerso();

        int[][] grille = g.getGrille();
        boolean isPlaced = false; // true <- Le bateau est placé correctement, on met à jour la grille

        switch (direction) {
            case "right":
                if (y + b.getTaille() < grille[x].length) // Ne place le bateau que si sa dernière case est comprise dans la ligne
                    for (int i = y; i < y + b.getTaille(); i++) {
                        if (grille[x][i] == 1) {
                            break; // Plus besoin de continuer si l'espace est déjà utilisé
                        } else {
                            grille[x][i] = 1;
                            isPlaced = true; //
                        }
                    }
                break;

            case "down":
                if (x + b.getTaille() < grille.length) // Ne place le bateau que si sa dernière case est comprise dans la colonne
                    for (int i = x; i < x + b.getTaille(); i++) {
                        if (grille[i][y] == 1) {
                            break;
                        } else {
                            grille[i][y] = 1;
                            isPlaced = true;
                        }
                    }
                break;
        }
        if (isPlaced) {
            this.inventaire.retirerBateau(b);
            System.out.printf("Le bateau %s est placé en (%s,%s).%n", b.getId(), x, y);
            g.updateGrille(grille);
        }
        else
            System.out.printf("Le bateau %s n'a pas été placé.%n", b.getId());
    }
}
