package traffic;

import java.util.Random;

public class Lane
  extends Thread
{
  private IBridge Bridge;
  private int TotalCars;
  private int MaxDelay;
  private long Seed;

  public Lane(IBridge _bridge, int _total_cars, int _max_delay, long _seed)
  {
    Bridge = _bridge;
    TotalCars = _total_cars;
    MaxDelay = _max_delay;
    Seed = _seed;
  }
  
  public void run()
  {
    Random rng = new Random(Seed);

    for (int i = 0; i < TotalCars; i++)
    {
      if (isOpen())
      {
        try
        {
          Thread.sleep(rng.nextInt(MaxDelay));
        }
        catch (InterruptedException _exception)
        {
          System.out.println("Thread interrupted while waiting for join.");
        }

        Bridge.enter();
      }
    }
  }

  protected boolean isOpen()
  {
    return true;
  }
}
