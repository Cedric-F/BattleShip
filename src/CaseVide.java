class CaseVide extends Case {

    CaseVide(int ligne, int colonne) {
        this.pos[0] = ligne;
        this.pos[1] = colonne;
        this.contenu = 0;
    }

    @Override
    int[] getPos() {
        return this.pos;
    }
}
