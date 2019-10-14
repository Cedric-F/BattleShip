import java.util.Scanner;

public class Test {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Création des joueurs et de leurs inventaires

        Joueur j1 = new Joueur("Foo");
        Joueur j2 = new Joueur("Bar");

        // Les joueurs placent leurs bateaux;

        placement(j1);
        placement(j2);

        // Test de tire

        for (int i = 0; i < 10; i++) {
            int x, y;
            System.out.println("Le joueur " + j1.getPseudo() + " attaque.");
            do {
                System.out.print("x : ");
                x = Integer.parseInt(sc.nextLine());
                System.out.print("y : ");
                y = Integer.parseInt(sc.nextLine());
            } while (x < 0 || x > 9 || y < 0 || y > 9);
            j1.tirer(x, y, j2);

            System.out.println("Le joueur " + j2.getPseudo() + " attaque.");
            do {
                System.out.print("x : ");
                x = Integer.parseInt(sc.nextLine());
                System.out.print("y : ");
                y = Integer.parseInt(sc.nextLine());
            } while (x < 0 || x > 9 || y < 0 || y > 9);
            j2.tirer(x, y, j1);
        }

        sc.close();
    }

    private static void placement(Joueur j) {
        Inventaire inv = j.getInventaire();

        int x, y;
        String direction;
        boolean isPlaced;

        Bateau bateau;

        System.out.println(String.format("Le joueur %s place ses bateaux:", j.getPseudo()));

        for (int i = 0; i < 5; i++) {
            bateau = inv.getBateau(i);
            do {
                System.out.println(String.format("Bateau n°%s, de taille %s", i, bateau.getTaille()));
                System.out.print("Position x : ");
                x = Integer.parseInt(sc.nextLine());
                System.out.print("Position y : ");
                y = Integer.parseInt(sc.nextLine());
                System.out.print("Direction [droite/bas] : ");
                direction = sc.nextLine();
                isPlaced = j.placer(bateau, x, y, direction);

            } while (!isPlaced);
        }
    }

}
