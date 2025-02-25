package com.shiyu.commons.utils.cache;

import com.github.benmanes.caffeine.cache.Cache;
import org.apache.commons.lang3.tuple.Pair;

public abstract class CacheHelper<K, V> {

    // 创建缓存实例
    public Cache<K, V> cache;

    // 获取缓存中的值
    public V get(K key) {
        return cache.getIfPresent(key);
    }

    // 向缓存中添加值
    public void put(K key, V value) {
        cache.put(key, value);
    }

    // 移除缓存中的值
    public void invalidate(K key) {
        cache.invalidate(key);
    }

    // 清除所有缓存
    public void invalidateAll() {
        cache.invalidateAll();
    }

    // 获取缓存的大小
    public long size() {
        return cache.estimatedSize();
    }

    // 示例：加载缓存值
    public V getOrLoad(K key) {
        return cache.get(key, this::defaultLoad); // 从数据库或其他来源加载数据
    }

    //创建缓存
    public abstract Cache<K, V> createCache();

    // 没有缓存赋新值
    public abstract V defaultLoad(K key);

    //创建缓存
    public abstract Pair<K, V> create();
}
