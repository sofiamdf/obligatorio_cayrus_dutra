package src.uy.edu.um.prog2.adt.hash;

import src.uy.edu.um.prog2.adt.hash.HashNode;
import src.uy.edu.um.prog2.adt.hash.MyHash;

import java.util.ArrayList;

public class MyHashImpl<K,V> implements MyHash<K, V> {

    private ArrayList<HashNode<K, V>> myArray;
    private int maxBuckets;
    private int size;

    public MyHashImpl() {
        myArray = new ArrayList<>();
        maxBuckets = 13; //numero inicial de buckets
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
            System.out.printf("value " + myArray.get(bucket).getValue() + " changed to " + newHash.getValue() + "\n");
            myArray.get(bucket).setValue(value);

        } // si no, insertamos el nuevo nodo
        else {
            myArray.set(bucket, newHash);
            System.out.printf(newHash.getValue() + " added " + "\n");
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
            System.out.println("El bucket asignado es nulo");
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
}
