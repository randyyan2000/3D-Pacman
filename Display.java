import java.awt.*;  //for Graphics
import java.awt.event.*;  //for Graphics
import javax.swing.*;  //for JComponent, JFrame
import java.io.*;
import java.net.*;
import javax.imageio.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class Display extends JComponent implements KeyListener
{
  public ArrayList<Shape> shapes;
  public Display linkedDisplay;
  public MobilePoint playerPoint;
  public Point light;
  public Double angle;
  private boolean alive;
  private boolean power;
  private long powerTime;
  
  private boolean rotateRight;
  private boolean rotateLeft;
  private boolean up;
  private boolean down;
  private boolean forward;
  private boolean right;
  private boolean back;
  private boolean left;
  private Integer score;
  private Font font;
  public DisplayThread displayThread;
  
  private Ghost blinky;
  private Ghost inky;
  private Ghost pinky;
  private Ghost clyde;
  public ArrayList<Ghost> ghosts;
  
  private boolean jump;
  
  private boolean print;
  
  private static Map<String, Image> images = new HashMap<String, Image>();
  
  public int type;
  private final double GRAVITY = 0.0003;
  private final double JUMPV = 0.0555;
  private final int VCONST = 3000;
  private final int HCONST = 300;
//  private final double ANGLE_SPEED = Math.toRadians(0.08);
  private final double SPEED = .08;
  private static final double PI = Math.PI;
  public static final double GSPEED = PI/100*4;
  private Stroke defaultStroke;
  
  
  public Display(int t) throws FontFormatException, IOException
  {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setFocusable(true);
    addKeyListener(this);
    if(t==0)
      setPreferredSize(new Dimension(28*20, 31*20));
    else
      setPreferredSize(new Dimension(1000, 600));
    frame.getContentPane().add(this);
    frame.pack();
    frame.setVisible(true);
    shapes = new ArrayList<Shape>();
    playerPoint = new MobilePoint(280,470, 1);
    light = new Point(1000,600,10);
    type = t;
    angle = new Double(0);
    alive = true;
    power = false;
    
    rotateRight = false;
    rotateLeft = false;
    forward = false;
    back = false;
    right = false;
    left = false;
    up = false;
    down = false;
    print = false;
    
//    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    blinky = new Ghost(280,230, "blinky0");
//    blinky = new Ghost(250,410, "blinky0");
//    blinky = new Ghost(250,110, "blinky0");
//    blinky.setVX(GSPEED);
    blinky.setDirection(Ghost.UP);
    
//    blinky = new Ghost(310,440, "blinky0");
//    blinky.setDirection(Ghost.UP);
    
    inky = new Ghost(250,290, "inky0");
//    inky.setVX(GSPEED);
    inky.setDirection(Ghost.RIGHT);
    
    pinky = new Ghost(280,290, "pinky0");
//    pinky.setVX(GSPEED);
    pinky.setDirection(Ghost.RIGHT);
    
    clyde = new Ghost(310,290, "clyde0");
//    clyde.setVX(-GSPEED);
    clyde.setDirection(Ghost.LEFT);
//    
    ghosts = new ArrayList<Ghost>();
    
    ghosts.add(blinky);
    ghosts.add(inky);
    ghosts.add(pinky);
    ghosts.add(clyde);
    
    add(blinky);
    add(inky);
    add(pinky);
    add(clyde);
    
    score = 0;
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    InputStream is =  this.getClass().getResourceAsStream("PressStart2P.ttf");
    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
    font =new Font("Press Start 2P", Font.PLAIN, 30);
    
    
    displayThread = new DisplayThread(this);
  }
  
  public static void test() throws FontFormatException, IOException
  {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PressStart2P.ttf")));
    System.out.println(Font.createFont(Font.TRUETYPE_FONT, new File("PressStart2P.ttf")).getFontName());
  }
  
  public void paintComponent(Graphics g)
  {
    if(type == 0)
      g.setColor(Color.BLACK);
    else
      g.setColor(Color.BLACK);
    g.fillRect(0, 0, getWidth(), getHeight());

    if(type==1)
    {
      Paint p = ((Graphics2D)g).getPaint();
      ((Graphics2D)g).setPaint(new GradientPaint(getWidth()/2,getHeight()/2,new Color(102, 51, 0),getWidth()/2,getHeight(), new Color(204, 102, 0)));
      g.fillRect(0,getHeight()/2-10,getWidth(),getHeight());
//    g.fillRect(0,0,getWidth(),getHeight());
      ((Graphics2D)g).setPaint(p);
    }
    for(int i =0 ; i<shapes.size() ; i++)
    {
      Shape s = shapes.get(i);
      if(shapes.get(i) instanceof Point)
        drawPoint(g, (Point)s);
      if(shapes.get(i) instanceof Line)
        drawLine(g, (Line)s);
      if(shapes.get(i) instanceof Wall)
        drawWall(g, (Wall)s);
    }
    if(type == 0)
    {
      g.setColor(Color.YELLOW);
      g.fillOval(getWidth()/2-7, getHeight()/2-7, 14, 14);
      
    }
    g.setColor(Color.WHITE);
    
    if(power)
    {
      g.setColor(Color.GREEN);
      g.drawLine(0,0,0,getHeight());
      g.drawLine(0,0,getWidth(),0);
      g.drawLine(0,getHeight(),getWidth(),getHeight());
      g.drawLine(getWidth(),0,getWidth(),getHeight());
    }
    
    g.setFont(font);
    g.drawString(""+score, 9,40);
    //AXES
      
//       g.drawLine(200,0,200,400);
//       g.drawLine(0,200,400,200);
//       
//    g.setColor(Color.RED);
//    g.drawOval(100, 100, 200, 200);
  }
  
  public void drawWall(Graphics g, Wall w)
  {
    if(type == 0)
    {
      drawLine(g, w.getOverheadLine());
    }
    if(type == 1)
    {
      Point[] points = w.getPoints();
      if(onScreen(points))
      {
        if(w.getFill() == 0)
        {
          Line[] lines = w.getLines();
          for(int i = 0; i<lines.length; i++)
          {
            drawLine(g,lines[i]);
          }
        }
        if(w.getFill() == 1)
        {
          ApparentPoint[] aPoints = new ApparentPoint[4];
          int[] x = new int[4];
          int[] y = new int[4];
          for(int i=0; i<4;i++)
          {
            aPoints[i] = getApparentPoint(points[i],true);
            x[i]=(int)aPoints[i].getX();
            y[i]=(int)aPoints[i].getY();
          }
          
          //Point light
//        double s = w.normal().unitV().dot(w.center().vector(light).unitV());
          
          //Plane Light
          double s = w.normal().unitV().dot((new Vector(1,2,0)).unitV());
          
          Color c = w.getColor();
          int red = (int)Math.abs(c.getRed()*s);
          int green = (int)Math.abs(c.getGreen()*s);
          int blue = (int)Math.abs(c.getBlue()*s);
          if(red>255)
            red=255;
          if(green>255)
            green=255;
          if(blue>255)
            blue=255;
          
          g.setColor(new Color(red,green,blue));
          g.fillPolygon(x,y,4);
          g.setColor(Color.WHITE);
        }
      }
    }
  }
  
  public static Image getImage(String name)
  {
    try
    {
      Image image = images.get(name);
      if (image == null)
      {
        URL url = Display.class.getResource(name);
        if (url == null)
          throw new RuntimeException("unable to load image:  " + name);
        image = ImageIO.read(url);
        images.put(name, image);
      }
      return image;
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  public ApparentPoint drawPoint(Graphics g, Point p)
  {
    if(type == 0)
    {
      ((Graphics2D)g).setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
      g.setColor(Color.WHITE);
//      g.fillOval(p.getX()-5, p.getY()-5, 10, 10);
      int toHoriz = (int)(p.relX(playerPoint) * Math.sin(angle) - p.relY(playerPoint) * Math.cos(angle));
      int onHoriz = (int)(p.relX(playerPoint) * Math.cos(angle) + p.relY(playerPoint) * Math.sin(angle));
      int r = 10;
      if(p instanceof Ghost)
      {
        g.drawImage(getImage(((Ghost)p).getImage()+".png"),
                    onHoriz+(getWidth()-30)/2, -toHoriz+(getHeight()-30)/2, 30,30, null);
        
      }
      else if(p instanceof Pellet)
      {
        if(((Pellet)p).power)
          r=15;
        g.fillOval(onHoriz+(getWidth()-r)/2, -toHoriz+(getHeight()-r)/2, r, r);
      }
      return new ApparentPoint(new Point(onHoriz+getWidth()/2-r/2.0,-toHoriz+getHeight()/2-r/2.0), r, toHoriz);
    }
    
    if(type == 1)
    {
      ((Graphics2D)g).setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
      g.setColor(Color.WHITE);
      double toHoriz = (p.relX(playerPoint) * Math.sin(angle) - p.relY(playerPoint) * Math.cos(angle));
      double onHoriz = (p.relX(playerPoint) * Math.cos(angle) + p.relY(playerPoint) * Math.sin(angle));
      if(toHoriz>0)
      {
        int screenx = (int)(HCONST * onHoriz / toHoriz);
        int screeny = (int)(-VCONST * p.relZ(playerPoint) / toHoriz);
        int screenr = (int)(400 / toHoriz);
        if(p instanceof Ghost)
        {
//          g.fillOval(screenx+getWidth()/2-screenr/2, getHeight()/2-5+screeny-screenr/2, screenr, screenr);
          screenr*=10;
          g.drawImage(getImage(((Ghost)p).getImage()+".png"),
                      screenx+getWidth()/2-screenr,
                      getHeight()/2-5+screeny-screenr-screenr,
                      screenr*2,
                      screenr*2,
                      null); 
        }
        if(p instanceof Pellet)
        {
          if(((Pellet)p).power)
            screenr = (int)(1600 / toHoriz);
          g.setColor(new Color(255, 255, 204));
          g.fillOval(screenx+getWidth()/2-screenr/2, getHeight()/2-5+screeny-screenr/2, screenr, screenr);
        }
//          g.drawLine(screenx+200, 200-appHeight, screenx+200, 200+appHeight);
        return new ApparentPoint(new Point(screenx+getWidth()/2, getHeight()/2-5+screeny),screenr,toHoriz);
      }
    }
    return null;
  }
  
  public ApparentPoint getApparentPoint(Point p, boolean b)
  {
    double toHoriz = (p.relX(playerPoint) * Math.sin(angle) - p.relY(playerPoint) * Math.cos(angle));
    double onHoriz = ((p.relX(playerPoint) * Math.cos(angle) + p.relY(playerPoint) * Math.sin(angle)));
//    System.out.println(toHoriz);
    if(toHoriz<0 && b)
    {
      double a = Math.atan2(p.relY(playerPoint),p.relX(playerPoint));
      double x = .1*Math.cos(a);
      double y = .1*Math.sin(a);
//      return getApparentPoint(new Point(p.getX(),playerPoint.getY()-y,p.getZ()));
//      return getApparentPoint(new Point(playerPoint.getX()-x,p.getY(),p.getZ()));
      return getApparentPoint(new Point(     (playerPoint.getX()-x)*Math.abs(Math.sin(angle)) + p.getX()*Math.abs(Math.cos(angle))     ,
                                             (playerPoint.getY()-y)*Math.abs(Math.cos(angle)) + p.getY()*Math.abs(Math.sin(angle))     ,
                                              p.getZ()), false);
    }
    int screenx = (int)(HCONST * onHoriz / toHoriz);
    int screeny = (int)(-VCONST * p.relZ(playerPoint) / toHoriz);
//    System.out.println("("+screenx+","+screeny+")");
    
    int screenr = (int)(Math.abs(400 / toHoriz));
    return new ApparentPoint(new Point(screenx+getWidth()/2, getHeight()/2-5+screeny),screenr,(p.relX(playerPoint) * Math.sin(angle) - p.relY(playerPoint) * Math.cos(angle)));
  }
  
  public void drawLine(Graphics g, Line l)
  {
    if(type == 0)
    {
      
//      int toHoriz = (int)(p.relX(playerPoint) * Math.sin(angle) - p.relY(playerPoint) * Math.cos(angle));
//      int onHoriz = (int)(p.relX(playerPoint) * Math.cos(angle) + p.relY(playerPoint) * Math.sin(angle));
//      g.fillOval(onHoriz+getWidth()/2-5, -toHoriz+getHeight()/2-5, 10, 10);
//      return new ApparentPoint(new Point(onHoriz+getWidth()/2-5,-toHoriz+getHeight()/2-5), 10, toHoriz);
      
      
      g.setColor(new Color(0, 115, 230));
      ((Graphics2D)g).setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
      Point a = l.getA();
      Point b = l.getB();
      int toHoriza = (int)(a.relX(playerPoint) * Math.sin(angle) - a.relY(playerPoint) * Math.cos(angle));
      int onHoriza = (int)(a.relX(playerPoint) * Math.cos(angle) + a.relY(playerPoint) * Math.sin(angle));
      int toHorizb = (int)(b.relX(playerPoint) * Math.sin(angle) - b.relY(playerPoint) * Math.cos(angle));
      int onHorizb = (int)(b.relX(playerPoint) * Math.cos(angle) + b.relY(playerPoint) * Math.sin(angle));
      if(onScreen(a,b))
      {g.setColor(Color.green);}
      g.drawLine(onHoriza+getWidth()/2, -toHoriza+getHeight()/2, onHorizb+getWidth()/2, -toHorizb+getHeight()/2);
    }
    if(type == 1)
    {
      g.setColor(Color.WHITE);
      ((Graphics2D)g).setStroke(defaultStroke);
      int[]x = new int[4];
      int[]y = new int[4];
      ApparentPoint a = getApparentPoint(l.getA(),true);
      ApparentPoint b = getApparentPoint(l.getB(),true);
      
      if(onScreen(l.getA(),l.getB()))
      {
        drawPoint(g,l.getA());
        drawPoint(g,l.getB());
        
//      System.out.println(a);
//      System.out.println(b);
        
        double relativeAngle = Math.atan((b.getY()-a.getY())/(b.getX()-a.getX()));
        x[0] = (int)(a.getX()+Math.cos(relativeAngle + PI/2)*a.getR()/2);
        x[1] = (int)(a.getX()-Math.cos(relativeAngle + PI/2)*a.getR()/2);
        x[3] = (int)(b.getX()+Math.cos(relativeAngle + PI/2)*b.getR()/2);
        x[2] = (int)(b.getX()-Math.cos(relativeAngle + PI/2)*b.getR()/2);
        
        y[0] = (int)(a.getY()+Math.sin(relativeAngle + PI/2)*a.getR()/2);
        y[1] = (int)(a.getY()-Math.sin(relativeAngle + PI/2)*a.getR()/2);
        y[3] = (int)(b.getY()+Math.sin(relativeAngle + PI/2)*b.getR()/2);
        y[2] = (int)(b.getY()-Math.sin(relativeAngle + PI/2)*b.getR()/2);
        
//      g.setColor(Color.RED);
        g.fillPolygon(x,y,4);
      }
    }
  }
  
  public static void main(String[] args) throws FontFormatException, IOException
  {
    Display display = new Display(1);
//    Display display3D = new Display(1);
    
//    Color c = new Color(0, 115, 230);
//    double h = 2.5;
//    display.add(new Wall(new Point(390,210), new Point(390,270), h, 1, c));
//    display.add(new Wall(new Point(270,50), new Point(300,50), h, 1, c));
   
    PMap.createMap(display);
    
    
    

    
//    display3D.shapes = display.shapes;
//   display3D.playerPoint = display.playerPoint;
// display3D.angle = display.angle;
// display3D.setLinkedDisplay(display);
// display.setLinkedDisplay(display3D);
    
//    display3D.add5points(160,80,1);
//    display3D.add5points(160,80,0);
//    display3D.add5points(160,160,0);
//    display3D.add5points(160,240,3);
//    display3D.add5points(80,240,0);
//    display3D.add5points(80,320,1);
//    display3D.add5points(160,320,1);
//    display3D.add5points(240,320,1);
//    display3D.add5points(320,320,2);
//    display3D.add5points(240,160,1);
//    display3D.add5points(240,160,0);
//    display3D.add5points(320,80,0);
//    for(int i = 155  ; i<=235 ; i+=20)
//      display.pointSet.add(new Point(i,50));
    display.repaint();
    try{Thread.sleep(1000);}
    catch (InterruptedException e){}
    display.play();
//    display3D.play();
  }
  
  public void add(Shape s)
  {shapes.add(s);}
  
  public boolean onScreen(Point...points)
  {
    for(int i=0; i<points.length; i++)
    {
      if((points[i].relX(playerPoint) * Math.sin(angle) - points[i].relY(playerPoint) * Math.cos(angle))>0)
        return true;
    }
    return false;
  }
  
  public void add5points(int x, int y, int d)
  {
    for(int i = 0; i<5; i++)
    {
      if(d==0)
        shapes.add(new Point(x, y+20*i));
      else if(d==1)
        shapes.add(new Point(x+20*i,y));
      else if(d==2)
        shapes.add(new Point(x, y-20*i));
      else if(d==3)
        shapes.add(new Point(x-20*i, y));
      else
        throw new RuntimeException("invalid direction");
    }
  }
  
  public void play()
  {
    System.out.println("Play");
    int i = 0;
    displayThread.start();
    while(alive == true)
    {
      try
      {
        Thread.sleep(1);
        if((System.nanoTime()-powerTime)>=10000000000L)
        {
          power = false;
          for(Ghost g:ghosts)
            g.setVulnerable(false);
          if(linkedDisplay!=null)
            linkedDisplay.setPower(false);
        }
        double mx = 0;
        double my = 0;
        double speed = SPEED;
        if(power)
          speed*=1.5;
        if(rotateRight == true)
        {}
//          angle += ANGLE_SPEED; //rotate right
        if(rotateLeft == true)
        {}
//          angle -= ANGLE_SPEED; //rotate left
          
        if(forward == true)
        {mx+=speed * Math.sin(angle); my+=-speed * Math.cos(angle);}
        
        if(back == true)
        {mx+=-speed * Math.sin(angle); my+=(speed * Math.cos(angle));}
        
        if(right == true)
        {mx+=(speed * Math.cos(angle)); my+=(speed * Math.sin(angle));}
        
        if(left == true)
        {mx+=(-speed * Math.cos(angle)); my+=(-speed * Math.sin(angle));}
        
        if(linkedDisplay!= null)
        {
          linkedDisplay.angle = angle;
          linkedDisplay.score = score;
        }
        if(print)
        {
//        System.out.println(playerPoint.getX()+","+playerPoint.getY());
//        System.out.println("A:"+getApparentPoint(((Line)shapes.get(shapes.size()-1)).getA()));
//        System.out.println("B:"+getApparentPoint(((Line)shapes.get(shapes.size()-1)).getB()));
        }
        if(up) // up arrow
          playerPoint.move(0,0,0.0000003);
        if(down) // down arrow
        {
          if(playerPoint.getZ()>1)
          {
            playerPoint.move(0,0,-0.0000003);
          }
        }
        if(jump&&playerPoint.getZ()<=1)
        {
          playerPoint.setVZ(JUMPV);
          playerPoint.setAZ(-GRAVITY);
        }
        
        if((i+1)%25==0)
        {
          for(Ghost g: ghosts)
          {
            if(g.onCross())
            {
              if(false)
              {
                boolean[] checks = checkWalls(g);
                
                Vector v = g.vector(playerPoint);
                if(Math.abs(v.getY())>Math.abs(v.getX()))
                  v.setX(0);
                if(Math.abs(v.getX())>Math.abs(v.getY()))
                  v.setY(0);
                v = v.unitV();
                System.out.println((v.getX()+","+v.getY()));
                if(Math.round(v.getY()) == -1 && checks[0])
                {
                  System.out.println("up");
                  g.setDirection(Ghost.UP);
                }
                else if(Math.round(v.getY()) == 1 && checks[2])
                {
                  System.out.println("down");
                  g.setDirection(Ghost.DOWN);
                }
                else if(Math.round(v.getX()) == -1 && checks[3])
                {
                  System.out.println("left");
                  g.setDirection(Ghost.LEFT);
                }
                else if(Math.round(v.getX()) == 1 && checks[1])
                {
                  System.out.println("right");
                  g.setDirection(Ghost.RIGHT);
                }
              }
              else
              {
                g.setX(Math.round(g.getX()));
                g.setY(Math.round(g.getY()));
                g.setDirection(pickDirection(checkWalls(g),g.getDirection()));
              }
            }
          }
        }
        
        
        Vector move = checkCollisions(new Vector(mx,my,0));
        for(Ghost g: ghosts)
          g.move();
        playerPoint.move(move.getX(),move.getY());
        playerPoint.move();
        if(playerPoint.getZ()<=1);
        {
          playerPoint.setAZ(0);
          playerPoint.setVZ(0);
          playerPoint.setZ(1);
        }
        i++;
      }
      catch(Exception e){}
    }
    
    int select = JOptionPane.CLOSED_OPTION;
    while(select == JOptionPane.CLOSED_OPTION)
    {
      select = JOptionPane.showOptionDialog(null,"Your Score: " + score+"\n Play Again?", "Game Over", 0, 3, null, new Object[] {"Maybe","Yes"}, null);
    }
    if(select == 1)
    {
      reset(true);
      PMap.createMap(this);
      try{Thread.sleep(3000);}
      catch (InterruptedException e){}
      play();
    }
  }
  
  public void keyPressed(KeyEvent e)
  {
    int k = e.getKeyCode();
    if(k == 87) //w
    {
      forward = true;
    }
    if(k == 68) //d
    {
      right = true;
    }
    if(k == 83) //s
    {
      back = true;
    }
    if(k == 65) //a
    {
//      playerPoint.move((int)(-SPEED * Math.cos(angle)), (int)(-SPEED * Math.sin(angle)));
      left = true;
    }
    if(k == 37)
    {
      rotateLeft = true;
      angle-=(Math.PI/2);
    }
    if(k == 39)
    {
      rotateRight = true;
      angle+=(Math.PI/2);
    }
    if(k == 38) // up arrow
      up = true;
    if(k == 40) // down arrow
      down = true;
    if(k == 80)
    {
      power = !power;
      for(Ghost g: ghosts)
        g.setVulnerable(!g.isVulnerable());
      powerTime = System.nanoTime();
    }
    if(k == 192)
    {
      print = true;
      System.out.println(playerPoint.getX()+","+playerPoint.getY()+","+playerPoint.getZ());
      boolean[] b = checkWalls(blinky);
      System.out.println("{"+b[0]+","+b[1]+","+b[2]+","+b[3]+"}");
      blinky.setDirection(pickDirection(checkWalls(blinky),blinky.getDirection()));
//      System.out.println(blinky.getX()+","+blinky.getY());
    }
    if(k == 32)
      jump = true;
    if(k==48)
    {
      type++;
      if(type==2)
        type = 0;
    }
//    
//    repaint();
//    if(linkedDisplay!=null)
//      linkedDisplay.repaint();
  }
  
  public void setLinkedDisplay(Display d)
  {
    linkedDisplay = d;
  }
  
  public void keyReleased(KeyEvent e)
  {
    int k = e.getKeyCode();
    if(k == 37)      // left arrow 
    {
      rotateLeft = false;
    }
    else if(k == 39) // right arrow
    {
      rotateRight = false;
    }
    else if(k == 87) //w
      forward = false;
    else if(k == 68) //d
      right = false;
    else if(k == 83) //s
      back = false;
    else if(k == 65) //a
      left = false;
    else if(k == 38) // up arrow
      up = false;
    else if(k == 40) // down arrow
      down = false;
    else if(k == 192)
      print = false;
    else if(k == 32)
      jump = false;
  }
  
  public void sort()
  {
    for(int i = 1; i < shapes.size(); i++)
    {
      Shape temp = shapes.get(i);
      double d = shapes.get(i).center().distance(playerPoint);
      int j;
      for(j = i-1; j>=0 && d > shapes.get(j).center().distance(playerPoint); j--)
      {
        shapes.set(j+1,shapes.get(j));
      }
      shapes.set(j+1,temp);
    }
  }
//  
//  public boolean collide(Wall w, Vector move)
//  {
//    double xo = w.getA().relX(playerPoint);
//    double yo = w.getA().relY(playerPoint);
//    double xn = xo+move.getX();
//    double yn = yo+move.getY();
//    if((xo>0 && xn<0)||(xo<0 && xn>0) || (yo>0 && yn<0)||(yo<0 && yn>0))
//    {
//      xo = w.getB().relX(playerPoint);
//      yo = w.getB().relY(playerPoint);
//      xn = xo+move.getX();
//      yn = yo+move.getY();
//      if((xo>0 && xn<0)||(xo<0 && xn>0) || (yo>0 && yn<0)||(yo<0 && yn>0))
//      {
//        return true;
//      }
//    }
//    return false;
//  }
  
  public Vector checkCollisions(Vector move)
  {
    int pelletCount = 0;
    for(int i=0; i<shapes.size(); i++)
    {
      if((shapes.get(i) instanceof Wall))
      {
        Wall w = (Wall)shapes.get(i);
        double axo = w.getA().relX(playerPoint);
        double ayo = w.getA().relY(playerPoint);
        double bxo = w.getB().relX(playerPoint);
        double byo = w.getB().relY(playerPoint);
        if((Math.signum(axo)==Math.signum(bxo)) != (Math.signum(ayo)==Math.signum(byo)))
        {
          double axn = axo-move.getX();
          double ayn = ayo-move.getY();
          double bxn = bxo-move.getX();
          double byn = byo-move.getY();
          if((Math.signum(axo)==Math.signum(bxo)) && Math.signum(axo)!=Math.signum(axn) && Math.signum(bxo)!=Math.signum(bxn))
          {
//            System.out.println("collide");
            move = new Vector(0,move.getY(),0);
          }
          if((Math.signum(ayo)==Math.signum(byo)) && Math.signum(ayo)!=Math.signum(ayn) && Math.signum(byo)!=Math.signum(byn))
          {
//            System.out.println("collide");
            move = new Vector(move.getX(),0,0);
          }
        }
        for(Ghost g:ghosts)
        {
          axo = w.getA().relX(g);
          ayo = w.getA().relY(g);
          bxo = w.getB().relX(g);
          byo = w.getB().relY(g);
          double adj = 19.999;
          if((Math.signum(axo)==Math.signum(bxo)))
          {
            if(axo<0)
            {
              axo+=adj;
              bxo+=adj;
            }
            if(axo>0)
            {
              axo-=adj;
              bxo-=adj;
            }
          }
          if((Math.signum(ayo)==Math.signum(byo)))
          {
            if(ayo<0)
            {
              ayo+=adj;
              byo+=adj;
            }
            if(ayo>0)
            {
              ayo-=adj;
              byo-=adj;
            }
          }
          if((Math.signum(axo)==Math.signum(bxo)) != (Math.signum(ayo)==Math.signum(byo)))
          {
            double axn = axo-g.moveV().getX();
            double ayn = ayo-g.moveV().getY();
            double bxn = bxo-g.moveV().getX();
            double byn = byo-g.moveV().getY();
//              System.out.println("y");
//            if(print)
//            {
//              System.out.print((Math.signum(axo)==Math.signum(bxo))+" ");
//              System.out.print((Math.signum(axo)!=Math.signum(axn))+" ");
//              System.out.println(Math.signum(bxo)!=Math.signum(bxn));
//            }
            if((Math.signum(axo)==Math.signum(bxo)) && Math.signum(axo)!=Math.signum(axn) && Math.signum(bxo)!=Math.signum(bxn))
            {
//              if(print)
//                System.out.println(w.getA().getX()+","+w.getA().getY()+","+w.getB().getX()+","+w.getB().getY());
              g.switchDirection();
            }
            if((Math.signum(ayo)==Math.signum(byo)) && Math.signum(ayo)!=Math.signum(ayn) && Math.signum(byo)!=Math.signum(byn))
            {
//              System.out.println("gc");
              g.switchDirection();
            }
          }
        }
      }
      if((shapes.get(i) instanceof Pellet))
      {
        pelletCount++;
        Pellet p = (Pellet)shapes.get(i);
        if(p.hDistance(playerPoint)<7.0)
        {
          int s=10;
          if(p.power)
          {
            s=50;
            power = true;
            for(Ghost g:ghosts)
              g.setVulnerable(true);
            if(linkedDisplay!=null)
              linkedDisplay.setPower(true);
            powerTime = System.nanoTime();
          }
          score+=s;
          shapes.remove(i);
        }
      }
      if((shapes.get(i) instanceof Ghost))
      {
        Ghost g=(Ghost)shapes.get(i);
        if(g.hDistance(playerPoint)<6.0)
        {
          if(g.isVulnerable())
          {
            g.setX(280);
            g.setY(290);
            g.setDirection(Ghost.UP);
            g.setVulnerable(false);
            score+=1000;
          }
          else
          {
            alive=false;
            type = 0;
          }
        }
      }
    }
    if(pelletCount==0)
    {
      reset(false);
      PMap.createMap(this);      
      try{Thread.sleep(3000);}
      catch (InterruptedException e){}
      play();
    }
    return move;
  }
  
  public boolean[] checkWalls(Ghost g)
  {
    boolean[] checks = new boolean[4];
    for(int d=0; d<4; d++)
    {
      checks[d] = true;
      for(int i=0; i<shapes.size(); i++)
      {
        if((shapes.get(i) instanceof Wall))
        {
          Wall w = (Wall)shapes.get(i);
          double axo = w.getA().relX(g);
          double ayo = w.getA().relY(g);
          double bxo = w.getB().relX(g);
          double byo = w.getB().relY(g);
          if(((Math.signum(axo)==Math.signum(bxo))  != (Math.signum(ayo)==Math.signum(byo)))
//             || ((Math.signum(axo)==Math.signum(bxo)) && (Math.signum(ayo)==0 || Math.signum(byo)==0)) ||
//              ((Math.signum(ayo)==Math.signum(byo)) && (Math.signum(axo)==0 || Math.signum(bxo)==0))
                )
          {
            double axn = axo;
            double ayn = ayo;
            double bxn = bxo;
            double byn = byo;
            if(d==0)
            {
              ayn+=30;
              byn+=30;
            }
            if(d==2)
            {
              ayn-=30;
              byn-=30;
            }
            if(d==1)
            {
              axn-=30;
              bxn-=30;
            }
            if(d==3)
            {
              axn+=30;
              bxn+=30;
            }
              
//              System.out.println("y");
//            if(print)
//            {
//              System.out.print((Math.signum(axo)==Math.signum(bxo))+" ");
//              System.out.print((Math.signum(axo)!=Math.signum(axn))+" ");
//              System.out.println(Math.signum(bxo)!=Math.signum(bxn));
//            }
            if((Math.signum(axo)==Math.signum(bxo)) && Math.signum(axo)!=Math.signum(axn) && Math.signum(bxo)!=Math.signum(bxn))
            {
              checks[d] = false;
              break;
            }
//            if((Math.signum(axo)==Math.signum(bxo) && Mat
            
            if((Math.signum(ayo)==Math.signum(byo)) && Math.signum(ayo)!=Math.signum(ayn) && Math.signum(byo)!=Math.signum(byn))
            {
              checks[d] = false;
              break;
            }
            
          }
        }
      }
    }
    if((int)Math.round(g.getX())== 280 && (int)Math.round(g.getY())== 230)
      checks[2] = false;
    return checks;
  }
  
  public int pickDirection(boolean[] d, int direction)
  {
    d[Ghost.switchDirection(direction)]=false;
    int c = 0;
    for(int i =0 ;i<4;i++)
    {
      if(d[i])
        c++;
    }
    if(c==0)
      return -1;
    int m = (int)(Math.random()*c)+1;
    int i = -1;
    while(m>0)
    {
      i++;
      if(d[i])
        m--;
    }
//    System.out.println(i);
    return i;
  }
  
  public void reset(boolean scoreR)
  {
    shapes = new ArrayList<Shape>();
    playerPoint.setX(280);
    playerPoint.setY(470);
    angle = new Double(0);
    alive = true;
    power = false;
    
    rotateRight = false;
    rotateLeft = false;
    forward = false;
    back = false;
    right = false;
    left = false;
    up = false;
    down = false;
    print = false;
    type = 1;
    
//    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    blinky.setX(280);
    blinky.setY(230);
//    blinky.setVX(GSPEED);
    blinky.setDirection(Ghost.RIGHT);
    
//    blinky = new Ghost(310,440, "blinky0");
//    blinky.setDirection(Ghost.UP);
    
    inky.setX(250);
    inky.setY(290);
//    inky.setVX(GSPEED);
    inky.setDirection(Ghost.RIGHT);
    
    
    pinky.setX(280);
    pinky.setY(290);
//    pinky.setVX(GSPEED);
    pinky.setDirection(Ghost.RIGHT);
    
    clyde.setX(310);
    clyde.setY(290);
//    clyde.setVX(-GSPEED);
    clyde.setDirection(Ghost.LEFT);
//    
    
//    ghosts = new ArrayList<Ghost>();
//    
//    ghosts.add(blinky);
//    ghosts.add(inky);
//    ghosts.add(pinky);
//    ghosts.add(clyde);
//    
    add(blinky);
    add(inky);
    add(pinky);
    add(clyde);
    
    if(scoreR)
      score = 0;
    
    
//    displayThread = new DisplayThread(this);
  }
  
  public void setPower(boolean b)
  {power = b;}
  
  public void keyTyped(KeyEvent e)
  {
  }
}