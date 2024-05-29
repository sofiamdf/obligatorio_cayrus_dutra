package uy.edu.um.prog2.adt.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.queue.*;

import static org.junit.jupiter.api.Assertions.*;

class QueueImplTest {
    private MyQueue<Integer> newQueue;
    @BeforeEach
    void setUp() {
        newQueue = new MyLinkedListImpl<>();
    }

    @Test
    public void enqueueAndSizeTest() {
        newQueue.enqueue(20);
        newQueue.enqueue(40);
        newQueue.enqueue(10);

        assertTrue(newQueue.contains(20));
        assertEquals(3, newQueue.size());
    }

    @Test
    public void dequeueTest() {
        newQueue.enqueue(20);
        newQueue.enqueue(40);
        newQueue.enqueue(10);

        try {
            assertEquals(20, newQueue.dequeue());
            assertEquals(40, newQueue.dequeue());
            assertEquals(10, newQueue.dequeue());
        } catch (EmptyQueueException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void emptyQueueDequeueTest() {
        assertThrows(EmptyQueueException.class, newQueue::dequeue);
    }
}