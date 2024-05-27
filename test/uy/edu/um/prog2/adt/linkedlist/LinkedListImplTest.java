package test.uy.edu.um.prog2.adt.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.uy.edu.um.prog2.adt.linkedlist.*;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListImplTest {

    private MyList<Integer> list;
    @BeforeEach
    public void setUp() {
        list = new MyLinkedListImpl<>();
    }
    @Test
    public void testAdd() {

        list.add(2);
        list.add(1);
        list.add(5);
        list.add(4);

        assertEquals(4, list.size());
        assertEquals(2, list.get(0));
    }

    @Test
    public void testContains() {
        list.add(2);
        list.add(1);
        list.add(5);
        list.add(4);

        assertTrue(list.contains(4));
        assertFalse(list.contains(3));
    }

    @Test
    public void testRemove() {
        list.add(2);
        list.add(1);
        list.add(5);
        list.add(4);

        list.remove(6);
        assertEquals(4, list.size());

        list.remove(2);
        assertEquals(3, list.size());
    }
}