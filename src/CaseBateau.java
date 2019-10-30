class CaseBateau extends Case {

    private Bateau bateau;
    private int id;

    CaseBateau(Bateau bateau, Case c) {
        this.bateau = bateau;
        this.pos = c.getPos();
        this.contenu = 1;
    }

    Bateau getBateau() {
        return this.bateau;
    }

    @Override
    int[] getPos() {
        return this.pos;
    }
}
