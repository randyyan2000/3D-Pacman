public class ApparentPoint
{
    private Point point;
    private int screenr;
    private double toHoriz;

    public ApparentPoint(Point p, int r, double h)
    {
        point = p; screenr = r; toHoriz=h;
    }

    public double getX()
    {
        return point.getX();
    }

    public double getY()
    {
        return point.getY();
    }

    public double getR()
    {
        return screenr;
    }

    public double getToHoriz()
    {return toHoriz;}

    public String toString()
    {
        String s = "";
        s+="("+point.getX()+","+point.getY()+")";
        return s;
    }
}