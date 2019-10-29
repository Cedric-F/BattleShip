class CaseBateau extends Case {

    private Bateau bateau;
    private int id;

    CaseBateau(Bateau bateau, Case c, int id) {
        this.bateau = bateau;
        this.pos = c.getPos();
        this.contenu = 1;
        this.id = id;
    }

    Bateau getBateau() {
        return this.bateau;
    }

    @Override
    int[] getPos() {
        return this.pos;
    }
}
