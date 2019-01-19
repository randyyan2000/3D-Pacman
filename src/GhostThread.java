
public class GhostThread extends Thread
{
  private Thread t;
  private Display d;
  
  public GhostThread(Display display)
  {
    d=display;
  }
  
  public void run()
  {
      System.out.println("Ghost thread");
      try 
      {
        for(Ghost g: d.ghosts)
        {
          if(g.onCross())
          {
            g.setDirection(d.pickDirection(d.checkWalls(g),g.getDirection()));
          }
        }
        Thread.sleep(50);
      }
      catch (InterruptedException e)
      {
        System.out.println("Thread interrupted.");
      }
//      System.out.println("Thread " +  threadName + " exiting.");
   }
  
  public void start()
  {
    System.out.println("Starting Ghost thread");
    if (t == null)
    {
      t = new Thread (this);
      t.start ();
    }
  }
  
  
}