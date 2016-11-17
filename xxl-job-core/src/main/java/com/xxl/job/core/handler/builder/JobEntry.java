package com.xxl.job.core.handler.builder;

import java.util.Map.Entry;

public class JobEntry<K,V> implements Entry<K,V> {

    private K key;
    private V value;

    public JobEntry(K k, V v) {
        this.key = k;
        this.value = v;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        this.value = value;
        return value;
    }

    public void setKey(K key){
       this.key = key;
    }

    @Override
    public String toString() {
        return "JobEntry [key=" + key + ", value=" + value + "]";
    }
}
