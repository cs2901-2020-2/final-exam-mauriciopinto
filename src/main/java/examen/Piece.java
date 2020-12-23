package examen;

import java.util.ArrayList;
import java.util.List;

public class Piece {
    private List<Point> points;

    public Piece (int type) {
        points = new ArrayList<>();
        if (type == 0 || type == 1 || type == 2 || type == 5 || type == 6) {
            this.points.add (new Point(0, 2));
        }
        if (type == 1 || type == 2 || type == 3 || type == 4 || type == 5 || type == 7) {
            this.points.add (new Point(0, 1));
        }
        if (type == 1 || type == 2 || type == 5 || type == 7) {
            this.points.add (new Point(0, 0));
        }
        if (type == 0 || type == 6) {
            this.points.add (new Point(1, 2));
        }
        if (type == 3 || type == 4 || type == 5 || type == 7) {
            this.points.add (new Point(1, 1));
        }
        if (type == 1) {
            this.points.add (new Point(1, 0));
        }
        if (type == 0 || type == 4 || type == 6) {
            this.points.add (new Point(2, 2));
        }
        if (type == 0 || type == 3 || type == 4 || type == 5 || type == 6 || type == 7) {
            this.points.add (new Point(2, 1));
        }
        if (type == 0 || type == 1 || type == 4) {
            this.points.add (new Point(2, 0));
        }
    }

    public List<Point> getPoints() {
        return points;
    }
}
