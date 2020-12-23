package examen;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static Scanner in = new Scanner(System.in);
    static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main (String[] args) {
        logger.info ("Quieres ingresar o jugar como anónimo? (0: ingresar | 1: anónimo");
        int option = in.nextInt();
        String username;
        if (option == 0) {
            logger.info("Ingresa tu usuario:");
            username = in.nextLine();
        }
        else
            username = "jugador1";
        logger.info("bienvenido " + username + "!");
        GameSession game = GameSession.getInstance();
        Player p1 = new Player(username);
    }
}
