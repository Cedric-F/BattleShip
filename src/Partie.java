import java.util.Scanner;
import java.util.regex.Pattern;

class Partie {

    private static Scanner sc = new Scanner(System.in);

    static void start() {
        Joueur j1 = new Joueur("Foo");
        Joueur j2 = new Joueur("Bar");

        placement(j1);
        for (Case[] c : j1.getPerso().getGrille()) {
            for (Case s : c)
                Main.concat("" + s.contenu);
            Main.concat("\n");
        }
        placement(j2);

        while (j1.getInventaire().getBateaux().size() != 0 || j2.getInventaire().getBateaux().size() != 0) {
            tire(j1, j2);
            tire(j2, j1);
        }

        sc.close();
    }

    private static void tire(Joueur attaquant, Joueur cible) {
        Main.concat(String.format("Le joueur %s attaque.\n", attaquant.getPseudo()));
        attaquant.tirer(setValue("x"), setValue("y"), cible);
    }

    private static void placement(Joueur j) {
        Inventaire inv = j.getInventaire();

        boolean isPlaced;

        Bateau bateau;

        Main.concat(String.format("Le joueur %s place ses bateaux\n", j.getPseudo()));

        for (int i = 1; i <= 5; i++) {
            bateau = inv.getBateau(i);
            do {
                Main.concat(String.format("Bateau n°%s, de taille %s\n", i, bateau.getTaille()));
                isPlaced = j.placer(bateau, setValue("x (ligne)"), setValue("y (colonne)"), setValue());
            } while (!isPlaced);
        }
    }

    private static int setValue(String position) {
        String value;
        do {
            Main.concat(String.format("Position %s : ", position));
            value = sc.nextLine();
        } while (!Pattern.compile("[0-9]").matcher(value).matches());
        return Integer.parseInt(value);
    }

    private static String setValue() {
        String dir;
        do {
            Main.concat("Direction [droite/bas] : ");
            dir = sc.nextLine();
        } while (!Pattern.compile("droite|bas").matcher(dir).matches());
        return dir;
    }

}
