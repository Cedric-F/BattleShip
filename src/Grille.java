class Grille {
    private Case [][] grille;

    /**
     * Génère un tableau à double entrée et le rempli de cases neutres (eau → 0)
     * @param taille dimension du tableau
     */
    Grille(int taille) {
        this.grille = new Case[taille][taille];
        for (int i = 0; i < taille; i++)
            for (int j = 0; j < taille; j++)
                this.grille[j][i] = new CaseVide(i, j);
    }

    /**
     * Renvoie le contenu de la case ciblée
     * @param x ligne
     * @param y colonne
     * @return contenu de (x, y)
     */
    Case contenuCase(int x, int y) {
        return this.grille[x][y];
    }

    /**
     * Renvoie la grille
     * @return tableau à double entrée
     */
    Case [][] getGrille() {
        return this.grille;
    }

    /**
     * Met la grille à jour
     * @param g nouvelle grille
     */
    void setGrille(Case[][] g) {
        this.grille = g;
    }
}
