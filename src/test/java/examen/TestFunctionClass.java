package examen;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class TestFunctionClass {

    @Test
    public void CreateInstanceTest () {
        GameSession session1 = GameSession.getInstance();
        GameSession session2 = GameSession.getInstance();
        Assert.assertEquals(session1, session2);
    }

    @Test
    public void CreatePieceTest () {
        Piece p1 = new Piece(1);
        Piece p2 = new Piece(2);
        Piece p3 = new Piece(3);
        Piece p4 = new Piece(4);
        Piece p5 = new Piece(5);
        Piece p6 = new Piece(6);
        Piece p7 = new Piece(7);
        Piece p8 = new Piece(8);

        //List<Point>;
    }

    @Test
    public void generatePointTest () {
        Point testPoint = new Point(1, 2);
        Assert.assertEquals(testPoint.getX(), 1);
        Assert.assertEquals(testPoint.getY(), 2);
    }

    @Test
    public void generateRandomPieceTest () {

    }

    @Test
    public void checksetPiece () {
        GameSession testGame = GameSession.getInstance();
        Player testplayer = new Player("user1");

        int points = testplayer.setNextPiece(3, 4);
        Assert.assertEquals(points, 0);
    }
}
