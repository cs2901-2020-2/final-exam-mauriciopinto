package examen;

import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameSession {
    static final Logger logger = Logger.getLogger(GameSession.class.getName());
    private static GameSession instance = null;
    private static int semaphore = 0;
    private int[][] board;
    private SecureRandom rand = new SecureRandom();
    private Piece currentPiece;
    private static final int POSSIBLE_PIECES = 8;
    private int status = 0;

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

    public Piece generateRandomPiece () {
        int newPiece = rand.nextInt(POSSIBLE_PIECES);
        logger.log(Level.INFO, "La siguiente pieza es de tipo: {0}", newPiece);
        return new Piece (rand.nextInt(POSSIBLE_PIECES));
    }

    public boolean checkBoard () {
        boolean lost = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (checkPiece (i, j))
                    lost = false;
            }
        }
        return lost;
    }

    public boolean checkPiece (int x, int y) {
        for (Point currentPoint : currentPiece.getPoints()) {
            if (x + currentPoint.getX() > 8 || y - currentPoint.getY() < 0 || x < 0 || x > 8 || y < 0 || y > 8)
                return false;
            else {
                if (board[x + currentPoint.getX()][y - currentPoint.getY()] == 1)
                    return false;
            }
        }
        return true;
    }

    public int checkMatch() {
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

    public int setPiece (int x, int y) {
        /*Revisar si se puede colocar en las coordenadas*/
        if (!checkPiece (x, y)) {
            logger.info ("Elige otro espacio!");
            return -1;
        }

        /*Llenar los espacios con la pieza*/
        for (Point currentPoint : currentPiece.getPoints()) {
            board[x + currentPoint.getX()][y - currentPoint.getY()] = 1;
        }

        /*Revisar si se logró obtener puntos*/
        int points = checkMatch();
        logger.log (Level.INFO, "Ganaste {0} puntos!", Integer.toString(points));

        /*Generar siguiente pieza*/
        currentPiece = generateRandomPiece();

        /*Revisar si es posible colocar la nueva pieza*/
        if (checkBoard ())
            status = 1;
        return points;
    }

    public int getStatus() {
        return status;
    }

    public int[][] getBoard () {
        return board;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void displayBoard () {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                line.append(Integer.toString(board[i][j]));
            }
            logger.info(line.toString());
            line = new StringBuilder();
        }
    }
}
