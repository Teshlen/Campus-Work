package traffic;

import base.ThreadID;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class Simulator
  implements IBridge
{
  private LinkedList<Thread> Lanes = new LinkedList<Thread>();
  
  public Simulator(int _lane_count, int _cars_per_lane, int _max_delay, long _seed)
  {
    // Create the primary random number generator
    Random rng = new Random(_seed);

    // Create the lanes
    for (int i = 0; i < _lane_count; i++)
      Lanes.add(new SmartLane(this, _lane_count, _cars_per_lane, _max_delay, rng.nextLong()));
  }
  
  public void run()
  {
    ListIterator<Thread> iterator = Lanes.listIterator();
    
    while (iterator.hasNext())
      iterator.next().start();
    
    while (iterator.hasPrevious())
    {
      try
      {
        iterator.previous().join();
      }
      catch (InterruptedException _exception)
      {
        System.out.println("Thread interrupted while waiting for join.");
      }
    }
  }

  // IBridge implementation
  // --------------------------------------------------------------------------

  public void enter()
  {
    System.out.println("ENTERED: " + Integer.toString(ThreadID.get()));
  }

  // --------------------------------------------------------------------------
  
  public static void main(String[] args)
  {
    Simulator simulator = new Simulator(
        Integer.parseInt(args[0]),
        Integer.parseInt(args[1]),
        Integer.parseInt(args[2]),
        Long.parseLong(args[3]));

    simulator.run();
  }
}
