package uy.edu.um.prog2.adt.hash;

public interface MyHash<K,V> {
    public void put(K key, V value);
    public boolean contains(K key);
    public void remove(K key) throws EntidadNoExiste;
}
