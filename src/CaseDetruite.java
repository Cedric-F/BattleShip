class CaseDetruite extends Case {

    CaseDetruite(Case c) {
        this.pos = c.getPos();
        this.contenu = -1;
    }

    @Override
    int[] getPos() {
        return this.pos;
    }
}
