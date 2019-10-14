//import java.util.ArrayList;
import java.util.HashMap;

class Inventaire {
    //private ArrayList<Bateau> Bateaux;
    private HashMap<Integer, Bateau> Bateaux;

    Inventaire() {
        //this.Bateaux = new ArrayList<>();
        this.Bateaux = new HashMap<>();
    }

    void ajouterBateau(Bateau b) {
        //this.Bateaux.add(b);
        this.Bateaux.put(b.getId(), b);
    }

    void retirerBateau(Bateau b) {
        this.getBateaux().remove(b.getId());
        System.out.printf("Bateau %s est retiré de l'inventaire.%n", b.getId());
    }

    Bateau getBateau(int id) {
        return this.getBateaux().get(id);
    }

    HashMap<Integer, Bateau> getBateaux() {
        return this.Bateaux;
    }
}
