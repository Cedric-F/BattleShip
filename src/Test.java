import java.util.Arrays;

public class Test {
    public static void main(String[] args) {

        Joueur j1 = new Joueur("Foo");
        Joueur j2 = new Joueur("Bar");

        // Test création de joueurs et de leurs inventaires

        j1.setInventaire();
        Inventaire j1Inv = j1.getInventaire();

        j2.setInventaire();
        Inventaire j2Inv = j2.getInventaire();

        // Test placement sur les grilles persos

        j1.placer(j1Inv.getBateau(0), 3, 0, "down");
        j1.placer(j1Inv.getBateau(3), 0, 0, "right");
        j2.placer(j2Inv.getBateau(0), 0, 0, "down");
        j2.placer(j2Inv.getBateau(4), 5, 5, "right");

        // Test de tire

        System.out.println(j1.tirer(5,5, j2) ? "Touché" : "Raté");
        System.out.println(j2.tirer(5,5, j1) ? "Touché" : "Raté");
        System.out.println(j2.tirer(5,5, j1) ? "Touché" : "Raté");
        System.out.println(j2.tirer(0,0, j1) ? "Touché" : "Raté");
        System.out.println(Arrays.deepToString(j2.getAttaque().getGrille()));

    }
}
