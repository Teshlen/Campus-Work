package traffic;

public class SmartLane
  extends Lane
{
  private int TotalLanes;

  public SmartLane(IBridge _bridge, int _total_lanes, int _total_cars, int _max_delay, long _seed)
  {
    super(_bridge, _total_cars, _max_delay, _seed);

    TotalLanes = _total_lanes;
  }

  @Override
  protected boolean isOpen()
  {
    // Implement your solution here...

    return true;
  }
}