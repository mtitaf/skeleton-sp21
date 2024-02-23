package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V>{

    /** single BST node */
    private  class BSTNode {
        private BSTNode left, right;
        private K key;
        private V value;

        private BSTNode (K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size;

    private BSTNode root;

    public BSTMap() {
        size = 0;
    }

    @Override
    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        BSTNode node = find(root, key);
        return node != null;
    }

    @Override
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        BSTNode node = find(root, key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    @Override
    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    @Override
    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        BSTNode node = insert(root, key, value);

        if (size == 0) {
            root = node;
        }
        size++;
    }

    @Override
    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private BSTNode find(BSTNode bst, K key) {
        if (bst == null) {
            return null;
        }
        if (key.equals(bst.key)) {
            return bst;
        } else if (key.compareTo(bst.key) < 0) {
            return find(bst.left, key);
        } else if (key.compareTo(bst.key) > 0) {
            return find(bst.right, key);
        }
        return null;
    }

    private BSTNode insert(BSTNode bst, K key, V value) {
        if (bst == null) {
            return new BSTNode(key, value);
        }
        if (key.compareTo(bst.key) < 0) {
            bst.left = insert(bst.left, key, value);
        } else if (key.compareTo(bst.key) > 0) {
            bst.right = insert(bst.right, key, value);
        }
        return bst;
    }
}
