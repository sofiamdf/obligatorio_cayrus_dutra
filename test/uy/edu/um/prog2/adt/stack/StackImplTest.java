package test.uy.edu.um.prog2.adt.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import src.uy.edu.um.prog2.adt.stack.*;

import static org.junit.jupiter.api.Assertions.*;

class StackImplTest{

    private MyStack<Integer> newStack;

    @BeforeEach
    void setUp() {
        newStack = new MyLinkedListImpl<>();
    }

    @Test
    public void pushAndPeekTest() {
        newStack.push(2);
        newStack.push(4);
        newStack.push(7);

        assertEquals(7, newStack.peek());
        assertEquals(3, newStack.size());
    }

    @Test
    public void popTest() {
        newStack.push(2);
        newStack.push(4);
        newStack.push(7);

        try {
            assertEquals(7, newStack.pop());
            assertEquals(4, newStack.pop());
            assertEquals(2, newStack.pop());
        } catch (EmptyStackException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void emptyStackPeekTest() {
        assertNull(newStack.peek());
    }

    @Test
    public void emptyStackPopTest() {
        assertThrows(EmptyStackException.class, newStack::pop);
    }
}



