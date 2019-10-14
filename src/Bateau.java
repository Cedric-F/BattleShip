import java.util.HashMap;

class Bateau {

    private int id;
    private int taille;
    private HashMap<Integer, Integer> parts;

    Bateau(int id, int taille) {
        this.id = id;
        this.taille = taille;
        this.parts = new HashMap<>();
    }

    int getId() {
        return this.id;
    }

    int getTaille() {
        return this.taille;
    }

}
