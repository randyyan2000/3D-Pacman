public class MobilePoint extends Point
{
  private double vx;
  private double vy;
  private double vz;
  
  private double ax;
  private double ay;
  private double az;
  
  
  public MobilePoint(double x, double y)
  {
    super(x,y);
    
    vx=0;
    vy=0;
    vz=0;
  }
  
  public MobilePoint(double x, double y, double z)
  {
    super(x,y,z);
    
    vx=0;
    vy=0;
    vz=0;
  }
  
  public MobilePoint(double x, double y, double z, double vx, double vy, double vz)
  {
    super(x,y,z);
    
    this.vx=vx;
    this.vy=vy;
    this.vz=vz;
  }
  
  public void setVX(double x)
  {vx=x;}
  
  public void setVY(double y)
  {vy=y;}
  
  public void setVZ(double z)
  {vz=z;}
  
  public void setAX(double x)
  {ax=x;}
  
  public void setAY(double y)
  {ay=y;}
  
  public void setAZ(double z)
  {az=z;}
  
  public void move()
  {
    setX(getX()+vx);
    vx+=ax;
    setY(getY()+vy);
    vy+=ay;
    setZ(getZ()+vz);
    vz+=az;
  }
  
  public Vector moveV()
  {
    return new Vector(vx,vy,vz);
  }
}