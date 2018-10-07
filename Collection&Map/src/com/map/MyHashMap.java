package com.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyHashMap<K, V> {

    /**
     *
     *  HashMap的核心方法
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        // 准备备用桶， 工作指针
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        //1、 判断当前的桶是否为空， 空的话需要初始化
        if ((tab = table) == null || (n = tab.length) == 0)
            // resize 方法会判断是否需要进行初始化
            n = (tab = resize()).length;
        //2、 与当前的桶里面的值没有hash 冲突
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else { //3、 与当前的桶里的值有hash冲突的分支
            Node<K,V> e; K k;
            // 判断当前桶中的key 和 hash 值是否与传入的key和hash值相等
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                // 写入的key和hash 值相等
                e = p; // 直接赋值给e, 跳到8
            //4、若是当前桶为红黑树，
            else if (p instanceof TreeNode)
                // 采用红黑树的方式写入数据
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                // 5、若为链表，
                for (int binCount = 0; ; ++binCount) {
                    // 将当前的key、value 封装新节点写到当前桶的后面，形成链表
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        // 6、判断当前链表的大小是否超过预设的阈值
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            // 超过的时候，转换成红黑树
                            treeifyBin(tab, hash);
                        break; // 退出当前循环
                    }
                    // 如果在遍历过程中找到与key相同时直接退出遍历
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e; // 不断寻找
                }
            }
            // 8、若e 不为空，表示当前存在相同的key，直接进行覆盖操作
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        // 9、 判断是否进行扩容操作
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }

    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

}
