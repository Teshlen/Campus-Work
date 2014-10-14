package simulation;

import java.util.Random;

public class Visitor
  extends Thread
{
  private IReporter Reporter = null;
  private Rooms Rooms = null;
  private int MaxVisits = 0;
  private int MaxDelay = 0;
  private Random RNG = null;

  public Visitor(IReporter reporter, Rooms rooms, int max_visits, int max_delay, long seed)
  {
    Reporter = reporter;
    this.Rooms = rooms;
    MaxVisits = max_visits;
    MaxDelay = max_delay;
    RNG = new Random(seed);
  }

  public void run()
  {
    for (int i = 0; i < MaxVisits; i++)
    {
      // Enter a randomly chosen room
      int room = RNG.nextInt(this.Rooms.getCount());
      Reporter.recordAction("ENTERING ROOM " + Integer.toString(room));
      Rooms.enter(room);
      Reporter.recordAction("ENTERED ROOM " + Integer.toString(room));

      // Delay for a random amount of time
      try
      {
        Thread.sleep(RNG.nextInt(MaxDelay));
      }
      catch (InterruptedException exception)
      {
        Reporter.recordAction("INTERRUPTED");
      }

      // Exit the room
      Reporter.recordAction("EXITING ROOM " + Integer.toString(room));
      Rooms.exit();
      Reporter.recordAction("EXITED ROOM " + Integer.toString(room));
    }
  }
}
