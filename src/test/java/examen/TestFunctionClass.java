package examen;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class TestFunctionClass {
    public void fillBoard (int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = 1;
            }
        }
    }

    @Test
    public void CreateInstanceTest () {
        GameSession session1 = GameSession.getInstance();
        GameSession session2 = GameSession.getInstance();
        Assert.assertEquals(session1, session2);
    }

    @Test
    public void createPlayerTest () {
        String username = "user1";
        Player testPlayer = new Player(username);
        Assert.assertEquals(testPlayer.getUsername(), username);
    }

    @Test
    public void CreatePieceTest () {
        List<Point> testPoints = new ArrayList<>();
        testPoints.add(new Point(0, 2));
        testPoints.add(new Point(1, 2));
        testPoints.add(new Point(2, 0));
        testPoints.add(new Point(2, 1));
        testPoints.add(new Point(2, 2));

        Assert.assertEquals(new Piece (1).getPoints(), testPoints);
    }

    @Test
    public void generatePointTest () {
        Point testPoint = new Point(1, 2);
        Assert.assertEquals(testPoint.getX(), 1);
        Assert.assertEquals(testPoint.getY(), 2);
    }

    @Test
    public void generateRandomPieceTest () {
        GameSession testGame = GameSession.getInstance();
        Assert.assertNotNull(testGame.generateRandomPiece());
    }

    @Test
    public void checkSetPiece () {
        GameSession testGame = GameSession.getInstance();
        Player testplayer = new Player("user1");

        testplayer.setPieceFixed(3, 4);
        Assert.assertEquals(testplayer.getPoints(), 0);
    }

    @Test
    public void checkStatusTest () {
        GameSession testGame = GameSession.getInstance();
        Player testPlayer = new Player("user1");

        fillBoard (testGame.getBoard ());
        Assert.assertTrue(testGame.checkBoard());
        testPlayer.setPieceFixed(3, 4);
        Assert.assertEquals(testGame.getStatus(), 1);
    }

    @Test
    public void checkCompletionsTest () {
        GameSession testGame = GameSession.getInstance();
        int[][] board = testGame.getBoard();
        for (int i = 0; i < 9; i++)
            board[0][i] = 1;
        Assert.assertEquals(testGame.checkMatch(), 120);
    }

    //@Test
    //public void
}
