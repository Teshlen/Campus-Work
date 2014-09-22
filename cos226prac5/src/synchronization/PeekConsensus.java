/**
 * Author : Uteshlen Nadesan ; student number: 28163304 ; Subject: cos 226;
 * practical: 5 date completed: 22/09/2014;
 */
package synchronization;

import base.ThreadID;
import java.util.LinkedList;

public class PeekConsensus
        extends ConsensusProtocol<Integer> {

    //using this list to store the ids
    private LinkedList<Integer> linkedList = new LinkedList<Integer>();

    public PeekConsensus(int threadCount) {
        super(threadCount);
    }

    private void print(int id, int x) {
        System.out.println("Thread " + id + " : " + x);
    }

    public Integer decide(Integer value) {
        int val;
        int threadId;
        threadId = ThreadID.get();

        this.propose(value);
        // To be completed...
        linkedList.add(threadId);
        print(threadId, threadId);

        val = linkedList.peek();
        print(threadId, val);

        return proposed.get(val);  // Replaced with the correct expression.
    }
}
