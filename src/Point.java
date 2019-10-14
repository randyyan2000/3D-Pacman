public class Point implements Shape
{
    private double x;
    private double y;
    private double z;

    public Point(double x, double y)
    {
        this.x=x;
        this.y=y;
        z=0;
    }

    public Point(double x, double y, double z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
    }



    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }


    public void move(double x, double y)
    {
        this.x+=x;
        this.y+=y;
    }

    public void move(double x, double y, double z)
    {
        this.x+=x;
        this.y+=y;
        this.z+=z;
    }
    public void setX(double x)
    {this.x=x;}
    public void setY(double y)
    {this.y=y;}
    public void setZ(double z)
    {this.z=z;}

    public boolean equals(Object obj)
    {
        if(((Point)obj).getX() == x && ((Point)obj).getY() == y)
            return true;
        return false;
    }

    @Override
    public Point center()
    {
        return this;
    }

    public int hashCode()
    {
        return (int)(x*y);
    }

    public double relX(Point p)
    {
        return x-p.getX();
    }

    public double relY(Point p)
    {
        return y-p.getY();
    }

    public double relZ(Point p)
    {
        return z-p.getZ();
    }



    public double distance(Point p)
    {
        double xd = x-p.getX();
        double yd = y-p.getY();
        double zd = z-p.getZ();
        return Math.sqrt(xd*xd+yd*yd+zd*zd);
    }

    public double hDistance(Point p)
    {
        double xd = x-p.getX();
        double yd = y-p.getY();
        return Math.sqrt(xd*xd+yd*yd);
    }

    public Vector vector(Point p)
    {
        return new Vector(p.getX()-x,p.getY()-y,p.getZ()-z);
    }
}