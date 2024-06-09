package uy.edu.um.prog2.adt.hash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashImplTest<K,V> {


    private MyHash<Integer, String> newHash;

    @BeforeEach
    void setUp() {
        newHash = new MyHashImpl<>();
    }

    @Test
    void testPut() {
        newHash.put(1, "A");
        newHash.put(2, "B");
        assertTrue(newHash.contains(1)); // check if A was placed in the list
        newHash.put(1,"C"); // check that Linked list works
        newHash.put(1,"D"); // check that Linked list works
        newHash.put(1,"D"); // check that Linked list works
    }

    @Test
    void testContains(){
        // Add elements to the hash table
        newHash.put(1, "A");
        newHash.put(2, "B");

        // Assert that the contains method returns true for existing keys
        assertTrue(newHash.contains(1)); // Check if key 1 exists
        assertTrue(newHash.contains(2)); // Check if key 2 exists

        // Assert that the contains method returns false for non-existing key
        assertFalse(newHash.contains(3)); // Check if key 3 doesn't exist
    }

    @Test
    void testRemove() throws EntidadNoExiste {
        // Add elements to the hash table
        newHash.put(1, "A");
        newHash.put(2, "B");

        // Remove an existing key
        newHash.remove(1);

        // Assert that the removed key is no longer present
        assertFalse(newHash.contains(1)); // Check if key 1 doesn't exist anymore

        // Assert that EntidadNoExiste works
        assertThrows(EntidadNoExiste.class, () -> {
            newHash.remove(3);
        });
    }
}





