public class Ghost extends MobilePoint
{
  private String image;
  private int direction;
  private boolean vulnerable;

  public static final int UP=0;
  public static final int RIGHT=1;
  public static final int DOWN=2;
  public static final int LEFT=3;

  public Ghost(double x, double y, String i)
  {
    super(x,y);
    image = i;
    vulnerable = false;
  }

  public String getImage()
  {
    String i = image;
    if(vulnerable)
      i = "vulnerable" + image.substring(image.length()-1);
    if(image.substring(image.length()-1).equals("0"))
      image = image.substring(0,image.length()-1)+"1";
    else if(image.substring(image.length()-1).equals("1"))
      image = image.substring(0,image.length()-1)+"0";
    return i;
  }

  public boolean onCross()
  {
    double dx = getX();
    double dy = getY();
    if(dy>190 && dy<390)
    {
      if(dx>450 || dx<110)
        setY(170);
    }

    if(getY()<10)
      setY(30);
    else if(getY()>610)
      setY(590);
    else if(getX()>550)
      setX(530);
    else if(getX()<10)
      setX(30);
    int x = (int)Math.round(getX());
    int y = (int)Math.round(getY());

    if(x==250 && y==290)
    {
      setDirection(1);
      return false;
    }
    if(   (((x-10)%30 == 0) || x==30 || x==530) && (((y+10)%30 == 0) || y==30)   )
    {
      if(x == 40 || x == 520)
      {
        if(y==470 || y==530)
          return true;
        else
          return false;
      }
      return true;
    }
    return false;
  }

  public void setDirection(int i)
  {
    direction = i;
    setVX(0);
    setVY(0);
    if(direction == UP)
      setVY(-Display.GSPEED);
    else if(direction == RIGHT)
      setVX(Display.GSPEED);
    else if(direction == DOWN)
      setVY(Display.GSPEED);
    else if(direction == LEFT)
      setVX(-Display.GSPEED);
  }

  public int getDirection()
  {return direction;}

  public void switchDirection()
  {
    direction= direction+2;
    if(direction == 4)
      direction = 0;
    else if(direction == 5)
      direction = 1;
    setDirection(direction);
  }

  public static int switchDirection(int d)
  {
    d+=2;
    if(d == 4)
      d = 0;
    else if(d == 5)
      d = 1;
    return d;
  }

  public void setImage(String i)
  {image=i;}

  public void setVulnerable(boolean v)
  {vulnerable = v;}

  public boolean isVulnerable()
  {return vulnerable;}
}