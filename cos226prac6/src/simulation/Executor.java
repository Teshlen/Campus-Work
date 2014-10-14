package simulation;

import java.lang.StringBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import base.ThreadID;

public class Executor
  implements IReporter
{
  private ConcurrentLinkedQueue<String> OutputBuffer = new ConcurrentLinkedQueue<String>();

  private Rooms Rooms = null;
  private LinkedList<Visitor> Visitors = new LinkedList<Visitor>();

  public Executor(int room_count, int visitor_count, int max_visits, int max_delay, long seed)
  {
    this.Rooms = new Rooms(room_count);

    // Create the visitors (threads)
    Random rng = new Random(seed);

    for (int i = 0; i < visitor_count; i++)
      Visitors.add(new Visitor(this, Rooms, max_visits, max_delay, rng.nextInt()));
  }

  public void run()
  {
    // Start the visitors (threads)
    ListIterator<Visitor> iterator = Visitors.listIterator();
    
    while (iterator.hasNext())
      iterator.next().start();
    
    // Wait for the visitors (threads) to finish executing
    iterator = Visitors.listIterator();
    boolean threads_live = true;
    
    while (threads_live)
    {
      threads_live = false;

      // Check the status of the visitors (threads)
      while (iterator.hasNext())
      {
        if (iterator.next().isAlive())
          threads_live = true;
      }

      // Print all pending output lines
      flushOutput();

      // Reset the iterator
      iterator = Visitors.listIterator();      
    }
  }

  public void recordAction(String action)
  {
    Date current_date = new Date();
    StringBuffer output_line = new StringBuffer(action);

    output_line.append(": [");
    output_line.append(Integer.toString(ThreadID.get()));
    output_line.append("] - ");
    output_line.append(Long.toString(current_date.getTime()));

    printOutput(output_line.toString());
  }

  private void printOutput(String line)
  {
    OutputBuffer.add(line);
  }

  private void flushOutput()
  {
    String line = OutputBuffer.poll();

    while (line != null)
    {
      System.out.println(line);

      line = OutputBuffer.poll();
    }
  }

	public static void main(String[] args)
  {
    Executor executor = new Executor(5,6,3,500,54541);
//      Integer.parseInt(args[0]),
//      Integer.parseInt(args[1]),
//      Integer.parseInt(args[2]),
//      Integer.parseInt(args[3]),
//      Long.parseLong(args[4]));

    executor.run();
  }
}