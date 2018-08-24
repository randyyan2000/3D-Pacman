import java.awt.*;

public class Line implements Shape
{
  private Point a;
  private Point b;
//  private Color color;
  
  public Line(Point a, Point b)
  {
    this.a=a;
    this.b=b;
//    color=Color.WHITE;
  }
  
  public Line(Point a, Point b, Color c)
  {
    this.a=a;
    this.b=b;
//    color=c;
  }
  
  public Line(double x1, double y1, double z1, double x2, double y2, double z2)
  {
    a = new Point(x1,y1,z1);
    b = new Point(x2,y2,z2);
  }
  
  public double distance(Point p)
  {
    return Math.min(a.distance(p),b.distance(p));
  }
  
  public Point center()
  {
    return new Point(((a.getX()+b.getX())/2),((a.getY()+b.getY())/2),((a.getZ()+b.getZ())/2));
  }
  
  public Point getA()
  {return a;}
  
  public Point getB()
  {return b;}
}