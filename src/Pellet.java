public class Pellet extends Point
{
    public boolean power;

    public Pellet(double x, double y, boolean t)
    {
        super(x,y,0.5);
        power = t;
    }
}