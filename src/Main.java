public class Main {
    private static String output = "";
    public static void main(String[] args) {
        Partie.start();
    }

    public static void concat(String s) {
        System.out.print(s);
        output += s;
    }
}
