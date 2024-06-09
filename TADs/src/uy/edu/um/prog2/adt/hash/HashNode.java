package uy.edu.um.prog2.adt.hash;


import java.util.Objects;

public class HashNode<K, V> {

    private K key;

    private V value;

    private HashNode<K, V> next;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;

    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public HashNode<K, V> getNext() {
        return next;
    }

    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashNode<?, ?> hashNode = (HashNode<?, ?>) o;
        return Objects.equals(key, hashNode.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
