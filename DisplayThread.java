public class DisplayThread extends Thread
{
  private Thread t;
  private Display d;
  
  public DisplayThread(Display display)
  {
    d=display;
  }
  
  public void run()
  {
      System.out.println("Running thread");
      try 
      {
        while(true)
        {
          d.sort();
          d.repaint();
          if(d.linkedDisplay != null)
          {
            d.linkedDisplay.sort();
            d.linkedDisplay.repaint();
          }
          // Let the thread sleep for a while.
          Thread.sleep(50);
        }
      }
      catch (InterruptedException e)
      {
        System.out.println("Thread interrupted.");
      }
//      System.out.println("Thread " +  threadName + " exiting.");
   }
  
  public void start()
  {
    System.out.println("Starting thread");
    if (t == null)
    {
      t = new Thread (this);
      t.start ();
    }
  }
  
  
}