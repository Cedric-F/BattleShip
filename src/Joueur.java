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

    String getPseudo() {
        return this.pseudo;
    }

    Inventaire getInventaire() {
        return this.inventaire;
    }

    private Grille getPerso() {
        return this.perso;
    }

    /**
     * Détruit la case cible sur la grille attaque de l'attaquant.
     * Vérifie le type de la case touchée sur la grille perso de la cible.
     * Si un bateau occupe cette case, celui-ci perd un point d'état.
     * Détruit le
     * @param ligne abscisse du point d'attaque
     * @param colonne ordonnée du point d'attaque
     * @param cible Joueur ciblé
     */
    void tirer(int ligne, int colonne, Joueur cible) {
        Grille attaque = this.attaque; // grille de l'attaquant
        Case[][] grilleAttaqueJoueur = attaque.getGrille();
        Case caseAttaque = grilleAttaqueJoueur[ligne][colonne];

        if (!(caseAttaque instanceof CaseDetruite)) { // si l'attaquant tire sur une nouvelle case

            Grille ciblePerso = cible.getPerso(); // grille de la cible
            Case[][] grillePersoCible = ciblePerso.getGrille();

            if (ciblePerso.contenuCase(ligne, colonne) instanceof CaseBateau) { // L'attaquant touche une case bateau

                Case caseCible = grillePersoCible[ligne][colonne]; // Récupère la case
                Bateau b = ((CaseBateau) caseCible).getBateau();

                System.out.println("Touché !");

                if (b.toucher(caseCible))  { // Détruit le bateau si coulé
                    System.out.println("Coulé !");
                    cible.getInventaire().retirerBateau(b);
                }

                grillePersoCible[ligne][colonne] = new CaseDetruite(caseCible);
                cible.perso.setGrille(grillePersoCible);
            } else {
                System.out.println("Manqué !");
            }

            grilleAttaqueJoueur[ligne][colonne] = new CaseDetruite(caseAttaque);
            attaque.setGrille(grilleAttaqueJoueur);

        } else { // L'attaquant a déjà tiré sur cette case
            System.out.println("Vous avez déjà tiré à ces coordonnées!");
        }
    }

    /**
     * Place un bateau sur la grille perso, en vérifiant si l'espace utilisé est libre et dans le plan
     * @param b Objet Bateau
     * @param ligne abscisse de départ
     * @param colonne ordonnée de départ
     * @param direction en ligne ou en colonne
     */
    boolean placer(Bateau b, int ligne, int colonne, String direction) {
        Grille g = this.perso;

        Case[][] grille = g.getGrille();
        boolean isPlaced = false;
        Case slot;

        b.setDirection(direction);

        try {
            switch (direction) {
                case "droite": // Ne place le bateau que si sa dernière case est comprise dans la ligne
                    for (int i = colonne, idCase = 1; i < colonne + b.getTaille(); i++) {
                        slot = grille[ligne][i];
                        if (slot instanceof CaseBateau) {
                            isPlaced = false;
                            break; // Plus besoin de continuer si l'espace est déjà utilisé
                        } else { // on crée une place bateau sur l'emplacement
                            grille[ligne][i] = new CaseBateau(b, slot, idCase);
                            isPlaced = true;
                        }
                        idCase++;
                    }
                    break;

                case "bas":
                    for (int i = ligne, idCase = 1; i < ligne + b.getTaille(); i++) {
                        slot = grille[i][colonne];
                        if (slot instanceof CaseBateau) {
                            isPlaced = false;
                            break;
                        } else {
                            grille[i][colonne] = new CaseBateau(b, slot, idCase);
                            isPlaced = true;
                        }
                        idCase++;
                    }
                    break;
            }
            if (isPlaced) {
                b.setPos(ligne, colonne);
                System.out.printf("Le bateau %s est placé en (%s,%s).%n", b.getId(), ligne, colonne);
                g.setGrille(grille);
            } else {
                System.out.println("Un bateau est déjà dans la zone.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Placement impossible.");
            return false;
        }

        return true;
    }
}
