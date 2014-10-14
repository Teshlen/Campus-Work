package simulation;

import base.ThreadID;
import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rooms {

    private int Count = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private boolean[] roomOccupied;
    private int[] roomsOccupiedInt;
    private Vector<Visit> vector;
    private int counter;

    public Rooms(int m) {
        Count = m;
        roomOccupied = new boolean[m];
        roomsOccupiedInt = new int[m];
        vector = new Vector<>();
        counter = 0;


        for (int i = 0; i < m; i++) {
            roomOccupied[i] = false;
        }
        for (int i = 0; i < m; i++) {
            roomsOccupiedInt[i] = 500;
        }


    }

    public int getCount() {
        return Count;
    }

    public synchronized void enter(int i) {
        lock.lock();

        vector.add(new Visit(ThreadID.get(), i));
        counter++;

        boolean flag = false;
        try {
            for (int u = 0; u < vector.capacity(); u++) {
                Visit x = vector.firstElement();
                if (roomOccupied[x.roomNumber] == false) {
                    for (int j = 0; j < roomOccupied.length; j++) {
                        if (j == x.roomNumber) {
                        } else if (roomOccupied[j] == true) {
                            flag = true;
                            notFull.await();
                        }
                    }
                }
                if (flag == true) {
                    vector.remove(0);
                }
            }
           if (Thread.interrupted())  // Clears interrupted status!
      throw new InterruptedException();

        } catch (InterruptedException ex) {
            Logger.getLogger(Rooms.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        finally {
            lock.unlock();
        }

    }

    public void exit() {
        lock.lock();
        for (int i = 0; i < roomOccupied.length; i++) {
            roomOccupied[i] = false;
        }
        try {
            notFull.signalAll();
        } finally {
            lock.unlock();
        }

    }
}

class Visit {

    public int threadID;
    public int roomNumber;

    public Visit(int x, int y) {
        threadID = x;
        roomNumber = y;
    }
}
