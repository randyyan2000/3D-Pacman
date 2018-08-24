public class Vector
{
  private double x;
  private double y;
  private double z;
  
  public Vector(double i, double j, double k)
  {
    x=i;
    y=j;
    z=k;
  }
  
  public double getX(){return x;}
  public double getY(){return y;}
  public double getZ(){return z;}
  public void setX(double x){this.x = x;}
  public void setY(double y){this.y = y;}
  
  public Vector crs(Vector v)
  {
    double x = getY()*v.getZ() - getZ()*v.getY();
    double y = getZ()*v.getX() - getX()*v.getZ();
    double z = getX()*v.getY() - getY()*v.getX();
    return new Vector(x,y,z);
  }
  
  public void opposite()
  {
    x=-x;
    y=-y;
    z=-z;
  }
  
  public double dot(Vector v)
  {
    return getX()*v.getX()+getY()*v.getY()+getZ()*v.getZ();
  }
  
  public double mag()
  {
    return Math.sqrt(x*x+y*y+z*z);
  }
  
  public Vector unitV()
  {
    double r = 1.0/mag();
    return new Vector(x*r,y*r,z*r);
  }
}