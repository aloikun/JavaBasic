package com.loikun;

import java.util.LinkedList;

/**
 * 求两个随机数组的交集
 */
public class Intersection {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        LinkedList<String> list2 = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            // 存储26个字母
            list.add(String.valueOf((char)('A' + Math.random() * ('Z' - 'A' + 1))));
            list2.add(String.valueOf((char)('A' + Math.random() * ('Z' - 'A' + 1))));
        }

        System.out.println(list);
        System.out.println(list2);
        list.retainAll(list2);

        System.out.println(list);

    }
}
