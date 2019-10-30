import java.lang.reflect.Array;
import java.util.Arrays;

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

    public Grille getPerso() {
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

                Main.concat("Touché !\n");

                if (b.toucher(caseCible))  { // Détruit le bateau si coulé
                    Main.concat("Coulé !\n");
                    cible.getInventaire().retirerBateau(b);
                }

                grillePersoCible[ligne][colonne] = new CaseDetruite(caseCible);
                cible.perso.setGrille(grillePersoCible);
            } else {
                Main.concat("Manqué !\n");
            }

            grilleAttaqueJoueur[ligne][colonne] = new CaseDetruite(caseAttaque);
            attaque.setGrille(grilleAttaqueJoueur);

        } else { // L'attaquant a déjà tiré sur cette case
            Main.concat("Vous avez déjà tiré à ces coordonnées!");
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
        boolean isPlaceable = false;
        Case slot;

        b.setDirection(direction);

        try {
            switch (direction) {
                case "droite":
                    // On récupère un sous ensemble de la ligne ciblée correspondant à la taille du bateau
                    Case[] row = Arrays.copyOfRange(grille[ligne], colonne, colonne + b.getTaille());
                    // On filtre les cases de la ligne. Si des cases occupées par un autre bateau sont présente, on ne peut pas placer le bateau actuel
                    isPlaceable = Arrays.stream(row).filter(c -> c instanceof CaseBateau).toArray().length == 0;

                    if (isPlaceable) { // Si la voie est libre on met à jour les cases avec la référence du bateau
                        for (int i = colonne; i < colonne + b.getTaille(); i++) {
                            slot = grille[ligne][i];
                            grille[ligne][i] = new CaseBateau(b, slot);
                        }
                    }
                    break;

                case "bas":
                    // On récupère un sous ensemble de la colonne ciblée
                    Case[] col = new Case[b.getTaille()];
                    for (int i = 0; i < col.length; i++)
                        col[i] = grille[i + ligne][colonne];

                    isPlaceable = Arrays.stream(col).filter(c -> c instanceof CaseBateau).toArray().length == 0;

                    if (isPlaceable) {
                        for (int i = ligne; i < ligne + b.getTaille(); i++) {
                            slot = grille[i][colonne];
                            grille[i][colonne] = new CaseBateau(b, slot);
                        }
                    }
                    break;
            }

            if (isPlaceable) {
                b.setPos(ligne, colonne);
                Main.concat(String.format("Le bateau %s est placé en (%s,%s).\n", b.getId(), ligne, colonne));
                g.setGrille(grille);
            } else {
                Main.concat("Un bateau est déjà dans la zone.\n");
                return false;
            }

        } catch (Exception e) {
            Main.concat("Impossible de sortir de la zone de combat!\n");
            return false;
        }

        return true;
    }
}
