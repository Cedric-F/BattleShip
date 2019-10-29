import java.util.Arrays;
import java.util.HashMap;

class Bateau {
    // Compteur statique pour générer les IDs des bateaux
    private static int compteurId = 1;

    private int id;
    private int taille;
    private int[] pos;
    private int etat;
    private String direction;

    Bateau(int taille) {
        this.id = compteurId > 5 ? compteurId - 5 : compteurId;
        this.taille = taille;
        this.etat = taille;
        this.pos = new int[2];
        compteurId++;
    }

    /*void setEtat() {
        *//*for (int i = 0; i < this.taille; i++) {
            if (this.direction.equals("droite"))
                this.etat.put(this.pos[1] + i, this.pos[0]);
            else
                this.etat.put(this.pos[0] + i, this.pos[1]);
        }*//*
    }*/

    int getEtat() {
        return this.etat;
    }

    int getId() {
        return this.id;
    }

    int getTaille() {
        return this.taille;
    }

    void setPos(int ligne, int colonne) {
        this.pos[0] = ligne;
        this.pos[1] = colonne;
    }

    int[] getPos() {
        return this.pos;
    }

    void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return this.direction;
    }

    /**
     * Vérifie l'intégrité du bateau après un tir adverse
     * En cas de touche, retire le morceau atteint
     * @param ligne position x du tir
     * @param colonne position y du tir
     * @return 1 si le bateau est touché, 0 si le bateau est coulé
     */
    /*void toucher(int ligne, int colonne) {
        try {
            switch (this.direction) {
                case "droite":
                    if (this.etat.get(colonne) == this.pos[0]) {
                        System.out.println(this.toString() + " - " + Arrays.toString(this.etat.keySet().toArray()));
                        this.etat.remove(colonne);
                        System.out.println(this.toString() + " - " + Arrays.toString(this.etat.keySet().toArray()));
                    }
                    break;
                case "bas":
                    if (this.etat.get(ligne) == this.pos[1]){
                        this.etat.remove(ligne);
                    }
                    break;
            }
        } catch (Exception e) {
        }
    }*/

    /**
     * Lorsqu'une case bateau est touché, le bateau en question perd un pv et la case est détruite.
     * @param c Case touchée occupée par le bateau
     * @return true si coulé
     */
    boolean toucher(Case c) {
        this.etat--;
        c = new CaseDetruite(c);
        return this.etat == 0;
    }

    public String toString() {
        return "B" + this.id;
    }

}