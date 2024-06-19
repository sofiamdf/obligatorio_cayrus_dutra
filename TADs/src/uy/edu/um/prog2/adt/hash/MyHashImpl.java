package uy.edu.um.prog2.adt.hash;

import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;

import java.util.ArrayList;

public class MyHashImpl<K,V> implements MyHash<K, V> {

    private ArrayList<HashNode<K, V>> myArray;
    private int maxBuckets;
    private int size;
    private int count;
//    private LinkedList<HashNode<K, V>> cells;

    @Override
    public int getSize(){
        return this.size;
    }
    @Override
    public V getIndex(int index){
        if (this.myArray.get(index) == null){
            return null;
        }
        return this.myArray.get(index).getValue();
    }

    @Override
    public V getValue(K key) {
        int position = getBucketPosition(key);
        HashNode<K, V> current = myArray.get(position);
        if (current == null) {
            return null;
        }
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }
        return null;
    }


    public void increaseCount(){
        count++;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public MyHashImpl() {
        myArray = new ArrayList<>();
        maxBuckets = 5000; //numero inicial de buckets
        size = 0;

        for (int i = 0; i < maxBuckets; i++) {
            myArray.add(null);
        }
    }



    private int getBucketPosition(K key) {
        int hashPosition = key.hashCode();
        return Math.abs(hashPosition % maxBuckets);
    }

    @Override
    public void put(K key, V value) {
        HashNode<K, V> newHash = new HashNode<>(key, value);
        int bucket = getBucketPosition(key);
        int initialBucket = bucket;

        // buscamos la posicion donde debe ir la clave
        while (myArray.get(bucket) != null && !myArray.get(bucket).equals(newHash)) {
            bucket = (bucket + 1) % maxBuckets;
        }
        // si la clave ya existe, cambiamos el valor
        if (myArray.get(bucket) != null && myArray.get(bucket).equals(newHash)) {
            HashNode<K,V> currentNode = myArray.get(bucket);
            HashNode<K,V> nextNode = currentNode.getNext();
            while(nextNode != null){
                currentNode = nextNode;
                nextNode = nextNode.getNext();
            }
            currentNode.setNext(newHash);

        } // si no, insertamos el nuevo nodo
        else {
            myArray.set(bucket, newHash);
            size++;
        }

        // resize si el tamano supera el 70% de su capacidad
        if ((1.0 * size) / maxBuckets >= 0.7) {
            ArrayList<HashNode<K, V>> temp = myArray;
            myArray = new ArrayList<>();
            maxBuckets = 2 * maxBuckets;
            size = 0;
            for (int i = 0; i < maxBuckets; i++) {
                myArray.add(null);
            }
            for (HashNode<K, V> node : temp) {
                if (node != null) {
                    put(node.getKey(), node.getValue());
                }
            }
        }
    }

    @Override
    public boolean contains(K key) {
        HashNode<K, V> newHash = new HashNode<>(key, null);
        int bucket = getBucketPosition(key);
        int initialBucket = bucket;

        // buscamos la clave, si no se encuentra devuelve false
        while (myArray.get(bucket) != null && !myArray.get(bucket).equals(newHash)) {
            bucket = (bucket + 1) % maxBuckets;
            if (bucket == initialBucket) {
                return false;
            }
        }

        // comprobamos si el bucket es nulo, si lo es, devuelve false
        if (myArray.get(bucket) == null){
            return false;
        }

        // si se encuentra la clave, devuelve true
        else {
            return myArray.get(bucket).equals(newHash);
        }
    }

    @Override
    public void remove(K key) throws EntidadNoExiste {
        HashNode<K, V> newHash = new HashNode<>(key, null);
        int bucket = getBucketPosition(key);
        int initialBucket = bucket;

        // se busca la clave, si no se encuentra lanza la excepcion EntidadNoExiste
        if (myArray.get(bucket) != null) {
            while (!myArray.get(bucket).equals(newHash)) {
                bucket = (bucket + 1) % maxBuckets;
                if (bucket == initialBucket) {
                    throw new EntidadNoExiste();
                }
            }

            // si lo encuentra, lo remueve
            myArray.remove(bucket);

            // reordena los nodos para evitar colisiones
            ArrayList<HashNode<K, V>> temp = myArray;
            myArray = new ArrayList<>();
            size = 0;
            for (int i = 0; i < maxBuckets; i++) {
                myArray.add(null);
            }
            for (HashNode<K, V> node : temp) {
                if (node != null) {
                    put(node.getKey(), node.getValue());
                }

            }
        } else {
            throw new EntidadNoExiste();
        }
    }

    public void resize(int newMaxBuckets){
        ArrayList<HashNode<K, V>> temp = myArray;
        myArray = new ArrayList<>();
        maxBuckets = newMaxBuckets;
        size = 0;
        for (int i = 0; i < maxBuckets; i++) {
            myArray.add(null);
        }
        for (HashNode<K, V> node : temp) {
            if (node != null) {
                put(node.getKey(), node.getValue());
            }
        }
    }

    @Override
    public MyList<V> get(K key) {
        int position = getBucketPosition(key);
        MyList<V> values = new MyLinkedListImpl<>();
        HashNode<K,V> current = myArray.get(position);
        if (current == null) {
            return null;
        }
        while(current.getNext() != null) {
            if(current.getKey() == key){
                values.add(current.getValue());
            }
            current = current.getNext();
        }
        return values;
    }

//    public MyList<V> get(K key) {
//        int position = getBucketPosition(key);
//        MyList<V> values = new MyLinkedListImpl<>();
//        HashNode<K,V> current = myArray.get(position);
//        if (current == null) {
//            return null;
//        }
//        while(current != null) {
//            if(current.getKey().equals(key)){
//                values.add(current.getValue());
//            }
//            current = current.getNext();
//        }
//        return values;
//    }
    @Override
    public int count(K key){
        int counter = 0;
        int bucket = getBucketPosition(key);
        int initialBucket = bucket;
        while (myArray.get(bucket) != null) {
            if (myArray.get(bucket).getKey().equals(key)) {
                counter++;
            }
            bucket = (bucket + 1) % maxBuckets;
        }
        return counter;
    }
}
