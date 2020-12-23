package examen;

import java.security.SecureRandom;
import java.util.logging.Logger;

public class GameSession {
    static final Logger logger = Logger.getLogger(GameSession.class.getName());
    private static GameSession instance = null;
    private static int semaphore = 0;
    private int[][] board;
    private SecureRandom rand = new SecureRandom();
    private Piece currentPiece;
    private final static int possiblePieces = 8;

    private GameSession () {
        board = new int[9][9];
        currentPiece = generateRandomPiece ();
    }

    private static synchronized int increaseSemaphore () {
        int prev = semaphore;
        semaphore++;
        return prev;
    }

    public static GameSession getInstance () {
        if (increaseSemaphore () == 0)
            instance = new GameSession();
        return instance;
    }

    private Piece generateRandomPiece () {
        int newPiece = rand.nextInt(possiblePieces);
        logger.info("La siguiente pieza es de tipo: " + newPiece);
        return new Piece (rand.nextInt(possiblePieces));
    }

    private boolean checkBoard () {
        boolean lost = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (checkPiece (i, j))
                    lost = false;
            }
        }
        return lost;
    }

    private boolean checkPiece (int x, int y) {
        for (Point currentPoint : currentPiece.getPoints()) {
            if (x - currentPoint.getX() < 0 || y + currentPoint.getY() > 9 || x < 0 || x > 9 || y < 0 || y > 9)
                return false;
            else {
                if (board[x - currentPoint.getX()][y + currentPoint.getY()] == 1)
                    return false;
            }
        }

        for (Point currentPoint : currentPiece.getPoints()) {
            board[x - currentPoint.getX()][y + currentPoint.getY()] = 1;
        }
        return true;
    }

    private int checkCompletions() {
        int points = 0;
        int completions = 0;
        boolean rowColBlockIsComplete;

        /*Check rows*/
        for (int i = 0; i < 9; i++) {
            rowColBlockIsComplete = true;
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0)
                    rowColBlockIsComplete = false;
            }
            if (rowColBlockIsComplete) {
                points += 120;
                completions++;
            }
        }

        /*Check columns*/
        for (int i = 0; i < 9; i++) {
            rowColBlockIsComplete = true;
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == 0)
                    rowColBlockIsComplete = false;
            }
            if (rowColBlockIsComplete) {
                points += 120;
                completions++;
            }
        }
        /*Check blocks*/
        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {
                rowColBlockIsComplete = true;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (board[i + k][j + l] == 0)
                            rowColBlockIsComplete = false;
                    }
                }
                if (rowColBlockIsComplete) {
                    points += 150;
                    completions++;
                }
            }
        }
        return points * completions;
    }

    public int setPiece (int x, int y, String username) {
        while (!checkPiece (x, y)) {
            logger.info ("Elige otro espacio!");
        }
        int points = checkCompletions();
        logger.info ("Ganaste " + Integer.toString(points) + " puntos!");
        currentPiece = generateRandomPiece();
        if (!checkBoard ())
            logger.info("Has perdido!");
        return points;
    }

    public void play (String username) {
        logger.info("Empieza el juego!");
    }
}
