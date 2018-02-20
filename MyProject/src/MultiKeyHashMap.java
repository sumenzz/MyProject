import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MultiKeyHashMap provides to store values with two level hierarchy of keys, 
 * super key (K1) and sub key (K2). The objects are inserted using super and sub keys.
 * It is not mandatory to use both keys as hierarchy, user can use two keys to store
 * the values and use either of the key to retrieve it.
 * 
 * @author Prathab K
 *
 */
public class MultiKeyHashMap<K1,K2,V> {
    
    /** Map structure holding another Map structure to implement MultiKeyHashMap*/
    private Map<K1,Map<K2,V>> mkMap;
    
    /** Initializes the MultiKeyHashMap */
    public MultiKeyHashMap(){
        mkMap = new HashMap<K1,Map<K2,V>>();
    }
    
    /**
     * Puts the value object based on the (super)key K1 and (sub)key K2
     * @param k1 key1 (super-key)
     * @param k2 key2 (sub-key)
     * @param v value object
     * @return previous value associated with specified key, or <tt>null</tt>
     *         if there was no mapping for key. 
     */
    public V put(K1 k1, K2 k2, V v) {
        Map<K2,V> k2Map = null;
        if(mkMap.containsKey(k1)) {
            k2Map = mkMap.get(k1);
        }else {
            k2Map = new HashMap<K2,V>();
            mkMap.put(k1, k2Map);
        }
        return k2Map.put(k2, v);
    }
    
    /**
     * Returns <tt>true</tt> if value object is present for the specified (super)key K1 and (sub)key K2
     * @param k1 key1 (super-key)
     * @param k2 key2 (sub-key)
     * @return <tt>true</tt> if value object present
     */
    public boolean containsKey(K1 k1, K2 k2) {
        if(mkMap.containsKey(k1)) {
            Map<K2,V> k2Map = mkMap.get(k1);
            return k2Map.containsKey(k2);
        }
        return false;
    }
    
    /**
     * Returns <tt>true</tt> if value object is present for the specified (super)key K1
     * @param k1 key1 (super-key)
     * @return <tt>true</tt> if value object present
     */
    public boolean containsKey(K1 k1) {
        return mkMap.containsKey(k1);
    }

    /**
     * Gets the value object for the specified (super)key K1 and (sub)key K2
     * @param k1 key1 (super-key)
     * @param k2 key2 (sub-key)
     * @return value object if exists or <tt>null</tt> if does not exists
     */
    public V get(K1 k1, K2 k2) {
        if(mkMap.containsKey(k1)) {
            Map<K2,V> k2Map = mkMap.get(k1);
            return k2Map.get(k2);
        }
        return null;
    }
    
    /**
     * Gets the value object for the specified (super)key K1
     * @param k1 key1 (super-key)
     * @return HashMap structure contains the values for the key k1 if exists 
     *         or <tt>null</tt> if does not exists
     */
    public Map<K2,V> get(K1 k1) {
        return mkMap.get(k1);
    }
    
    /**
     * Gets the value object for the specified (sub)key K2
     * @param k2 key2 (sub-key)
     * @return value object if exists or <tt>null</tt> if does not exists
     */
    public V getBySubKey(K2 k2) {
        for(Map<K2,V> m : mkMap.values()) {
            if(m.containsKey(k2))
                return m.get(k2);
        }
        return null;
    }
    
    /**
     * Removes the value object for the specified (super)key K1 and (sub)key K2
     * @param k1 key1 (super-key)
     * @param k2 key2 (sub-key)
     * @return previous value associated with specified key, or <tt>null</tt>
     *         if there was no mapping for key.
     */
    public V remove(K1 k1, K2 k2) {
        if(mkMap.containsKey(k1)) {
            Map<K2,V> k2Map = mkMap.get(k1);
            return k2Map.remove(k2);
        } 
        return null;
    }
    
    /**
     * Removes the value object(s) for the specified (super)key K1 
     * @param k1 key1 (super-key)
     * @return previous value (HashMap structure) associated with specified key, or <tt>null</tt>
     *         if there was no mapping for key.
     */
    //This might remove the contents of sub-map without user-knowledge
    private Map<K2,V> remove(K1 k1) {
        return mkMap.remove(k1);
    }
    
    /**
     * Size of MultiKeyHashMap
     * @return MultiKeyHashMap size
     */
    public int size() {
        int size = 0;
        for(Map<K2,V> m : mkMap.values()) {
            size++;
            size += m.size();
        }
        return size;
    }
    
    /**
     * Returns all the value objects in the MultiKeyHashMap
     * @return value objects as List
     */
    public List<V> getAllItems(){
        List<V> items = new ArrayList<V>();
        for(Map<K2,V> m : mkMap.values()) {
            for(V v : m.values()) {
                items.add(v);
            }
        }
        return items;
    }
    
    /**
     * Clears the entire hash map
     */
    public void clear() {
        for(Map<K2,V> m : mkMap.values())
            m.clear();
        
        mkMap.clear();
    }
    
}