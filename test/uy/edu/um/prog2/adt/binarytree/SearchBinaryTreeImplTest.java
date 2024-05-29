package uy.edu.um.prog2.adt.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.binarytree.MySearchBinaryTree;
import uy.edu.um.prog2.adt.binarytree.MySearchBinaryTreeImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;

import static org.junit.jupiter.api.Assertions.*;

class SearchBinaryTreeImplTest {
    private MySearchBinaryTree<Integer, Integer> newTree;

    @BeforeEach
    public void setUp() {
        newTree = new MySearchBinaryTreeImpl<>();
    }

    @Test
    public void addTest() {
        newTree.add(1, 1);
        newTree.add(5, 5);
        newTree.add(3, 3);
        newTree.add(2, 2);

        MyList<Integer> orderedList = newTree.inOrder();
        assertEquals(1, orderedList.get(0));
        assertEquals(2, orderedList.get(1));
        assertEquals(3, orderedList.get(2));
        assertEquals(5, orderedList.get(3));

    }

    @Test
    public void testRemove() {
        newTree.add(1, 1);
        newTree.add(5, 5);
        newTree.add(3, 3);
        newTree.add(2, 2);

        newTree.remove(2);
        newTree.remove(1);

        MyList<Integer> orderedList = newTree.inOrder();

        assertEquals(3, orderedList.get(0));
        assertEquals(5, orderedList.get(1));
    }

    @Test
    public void testRemoveSize() {
        newTree.add(3, 3);
        newTree.add(-1, -1);
        newTree.add(4, 4);

        newTree.remove(3);
        newTree.remove(-1);
        newTree.remove(4);

        MyList<Integer> orderedList = newTree.inOrder();
        assertEquals(0, orderedList.size());
    }

    @Test
    public void testFind() {
        newTree.add(3, 3);
        newTree.add(21, 21);
        newTree.add(11, 11);
        newTree.add(-1, -1);
        newTree.add(4, 4);

        newTree.remove(3);
        newTree.remove(-1);

        Integer number = newTree.find(3);
        assertNull(number);

        number = newTree.find(21);
        assertEquals(21, number);
    }

    @Test
    public void testContains() {
        newTree.add(3, 3);
        newTree.add(21, 21);
        newTree.add(11, 11);
        newTree.add(-1, -1);
        newTree.add(4, 4);

        assertFalse(newTree.contains(33));
        assertTrue(newTree.contains(-1));
    }
}

