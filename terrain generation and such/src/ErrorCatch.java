import java.util.Set;
public class ErrorCatch implements Runnable
{
  TPanel worldPanel;
  World world;
  Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
  Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
  int basicThreads = threadArray.length+1;
  public ErrorCatch(TPanel worldPanel,World world)
  {
    this.worldPanel=worldPanel;
    this.world=world;
  }
  public void run()
  {
    while(true)
    {
      threadSet = Thread.getAllStackTraces().keySet();
      threadArray = threadSet.toArray(new Thread[threadSet.size()]);
      System.out.println("Errors Detected, waiting to recreate, threads running:"+(threadArray.length-basicThreads));
      //System.out.println(threadSet);
      if(threadArray.length<=basicThreads)
      {
        System.out.println("Errors Detected, recreating world");
        new Thread(world).start();
        world.errorBool = false;
        break;
      }
      try{Thread.sleep(1000);}catch(InterruptedException e){}
    }
  }
}