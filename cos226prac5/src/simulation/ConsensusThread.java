package simulation;

import base.ThreadID;
import synchronization.Consensus;

public class ConsensusThread
  extends Thread
{
	private Consensus<Integer> ConsensusObject;
	private Integer InputValue;
	private int Delay;

	public ConsensusThread(Consensus<Integer> consensusObject, int inputValue, int delay)
	{
		ConsensusObject = consensusObject;
		InputValue = new Integer(inputValue);
		Delay = delay;
	}

	public void run()
	{
		// Sleep for the duration specified by Delay
		try
		{
  		Thread.sleep(Delay);
		}
		catch (InterruptedException exception)
		{
			System.out.println("Thread interrupted while sleeping.");
		}

		// Report before calling decide()
		System.out.println("Thread " + Integer.toString(ThreadID.get()) + 
			" : " + InputValue.toString() + ", " + Integer.toString(Delay));

		// Execute the consensus protocol
		Integer decision_value = ConsensusObject.decide(InputValue);

		// Report after calling decide()
		System.out.println("Thread " + Integer.toString(ThreadID.get()) + 
			" : " + decision_value.toString());
	}
}
