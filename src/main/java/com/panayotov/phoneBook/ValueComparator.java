package com.panayotov.phoneBook;

import java.util.*;

public class ValueComparator {

    public static <K, V extends Comparable> List<Map.Entry<K, V>> keysSortedByValue(Map<K, V> map) {
        Comparator<Map.Entry<K, V>> byMapValues = new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> left, Map.Entry<K, V> right) {
                return left.getValue().compareTo(right.getValue());
            }
        };

        List<Map.Entry<K, V>> keys = new ArrayList<>();

        keys.addAll(map.entrySet());

        Collections.sort(keys, byMapValues);
        Collections.reverse(keys);

        return keys;
    }
}
