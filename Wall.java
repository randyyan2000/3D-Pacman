import java.awt.*;

public class Wall implements Shape
{
  private Point a;
  private Point b;
  private double height;
  private int fill;
  private Color color;
  
  public Wall(Point a, Point b, double h)
  {
    this.a=a;
    this.b=b;
    height=h;
    fill=0;
    color = Color.WHITE;
  }
  
  public Wall(Point a, Point b, double h, int f, Color c)
  {
    this.a=a;
    this.b=b;
    height=h;
    fill=f;
    color = c;
  }
  
  public Point getA()
  {return a;}
  
  public Point getB()
  {return b;}
  
  public Point[] getPoints()
  {
    Point[] points = new Point[4];
    points[0]=new Point(a.getX(),a.getY(),0);
    points[1]=new Point(a.getX(),a.getY(),height);
    points[2]=new Point(b.getX(),b.getY(),height);
    points[3]=new Point(b.getX(),b.getY(),0);
    return points;
  }
  
  public Line[] getLines()
  {
    Point[] points = getPoints();
    Line[] lines = new Line[4];
    lines[0] = new Line(points[0],points[1]);
    lines[1] = new Line(points[1],points[2]);
    lines[2] = new Line(points[2],points[3]);
    lines[3] = new Line(points[3],points[0]);
    return lines;
//    if(fill == 1)//Filled
//    {
//      
//    }
  }
  
  public Color getColor()
  {
    return color;
  }
  
  public int getFill()
  {
    return fill;
  }
  
  public void setFill(int i)
  {fill = i;}
  
//  public void set
  
  public Line getOverheadLine()
  {
    return new Line(a,b);
  }
  
  public Vector normal()
  {
    Vector u = new Vector(b.getX()-a.getX(),b.getY()-a.getY(),0);
    Vector v = new Vector(0,0,height);
    Vector n = u.crs(v);
    if(n.getX()<0)
      n.opposite();
    return n;
  }
  
  public double distance(Point p)
  {
    Point[] points = getPoints();
    double min = points[0].distance(p);
    for(int i = 1; i<4; i++)
    {
      double d = points[i].distance(p);
      if(d<min)
        min = d;
    }
    return min;
  }
  
  public Point center()
  {
    return new Point(((a.getX()+b.getX())/2),((a.getY()+b.getY())/2),height/2);
  }
}