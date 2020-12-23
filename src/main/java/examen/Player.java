package examen;

public class Player {
    private String username;
    private int points = 0;
    private GameSession session;

    public Player (String username) {
        this.username = username;
        session = GameSession.getInstance();
    }

    public int setNextPiece ( int x, int y) {
        points += session.setPiece (x, y, username);
        return points;
    }
}
