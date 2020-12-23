package examen;

import java.util.List;

public class Piece {
    private List<Point> points;

    public Piece (int type) {
        switch (type) {
            case 0:
                this.points.add (new Point(0, 2));
                this.points.add (new Point(1, 2));
                this.points.add (new Point(2, 2));
                this.points.add (new Point(2, 1));
                this.points.add (new Point(2, 0));
                break;
            case 1:
                this.points.add(new Point(0, 0));
                this.points.add (new Point(0, 1));
                this.points.add (new Point(0, 2));
                this.points.add (new Point(1, 0));
                this.points.add (new Point(2, 0));
                break;
            case 2:
                this.points.add (new Point(0, 0));
                this.points.add (new Point(0, 1));
                this.points.add (new Point(0, 2));
                break;
            case 3:
                this.points.add (new Point(0, 1));
                this.points.add (new Point(1, 1));
                this.points.add (new Point(2, 1));
                break;
            case 4:
                this.points.add (new Point(0, 1));
                this.points.add (new Point(1, 1));
                this.points.add (new Point(2, 0));
                this.points.add (new Point(2, 1));
                this.points.add (new Point(2, 2));
                break;
            case 5:
                this.points.add (new Point(0, 0));
                this.points.add (new Point(0, 1));
                this.points.add (new Point(0, 2));
                this.points.add (new Point(1, 1));
                this.points.add (new Point(2, 1));
                break;
            case 6:
                this.points.add (new Point(0, 2));
                this.points.add (new Point(1, 2));
                this.points.add (new Point(2, 1));
                this.points.add (new Point(2, 2));
                break;
            case 7:
                this.points.add (new Point(0, 0));
                this.points.add (new Point(0, 1));
                this.points.add (new Point(1, 1));
                this.points.add (new Point(2, 1));
                break;
            default:
                break;
        }
    }

    public List<Point> getPoints() {
        return points;
    }
}
