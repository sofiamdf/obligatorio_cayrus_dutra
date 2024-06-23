package uy.edu.um.prog2.adt.hash;

import uy.edu.um.prog2.adt.linkedlist.MyList;

public interface MyHash<K,V> {
    int getSize();

    public void put(K key, V value);

    public boolean contains(K key);

    public void remove(K key) throws EntidadNoExiste;

    V getIndex(int index);

    MyList<V> get(K key);

    int count(K key);

    V getValue(K key);
}
