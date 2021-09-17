package me.hammer86gn.dfessentials.utils;

import java.util.Map;

public interface IMapManager<K,V> {

    void register(V v);

    void registerAll(V[] vs);

    void remove(K k);

    V getValueByKey(K k);

    Map<K,V> getAllEntries();

}
