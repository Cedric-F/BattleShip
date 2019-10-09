class Grille {
    private int [][] grille;
    Grille(int taille) {
        this.grille = new int[taille][taille];
        for (int i = 0; i < taille; i++)
            for (int j = 0; j < taille; j++)
                this.grille[j][i] = 0;
    }

    int contenuCase(int x, int y) {
        return this.grille[x][y];
    }

    int [][] getGrille() {
        return this.grille;
    }

    void updateGrille(int[][] g) {
        this.grille = g;
    }
}
