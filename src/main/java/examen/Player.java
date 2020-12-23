package examen;

import java.util.Scanner;
import java.util.logging.Logger;

public class Player {
    static final Logger logger = Logger.getLogger(Player.class.getName());
    private static Scanner in = new Scanner(System.in);
    private String username;
    private int points = 0;
    private GameSession session;

    public Player (String username) {
        this.username = username;
        session = GameSession.getInstance();
    }

    public int setNextPiece () {
        logger.info ("Elige la coordenada x: ");
        int x = in.nextInt();
        logger.info ("Elige la coordenada y: ");
        int y = in.nextInt();
        int result = session.setPiece (x, y);
        while (result != -1) {
            result = session.setPiece(x, y);
        }
        return points;
    }

    public int getPoints () {
        return points;
    }

    public void play () {
        while (session.getStatus() == 0) {
            setNextPiece();
        }
    }

    public String getUsername () {
        return username;
    }

    public void setPieceFixed (int x, int y) {
        session.setPiece(x, y);
    }
}
