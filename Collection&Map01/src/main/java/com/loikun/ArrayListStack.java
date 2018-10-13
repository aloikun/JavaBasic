package com.loikun;

import java.util.*;

/**
 * ArrayList 实现Stack以及Queue 的功能
 * 1、继承ArrayList 实现IStack 接口
 * 2、实现push，pop , 重写size方法
 */
public class ArrayListStack extends ArrayList implements IStack {
    private ArrayList arrayList = new ArrayList();

    @Override
    public void push(Object obj) {
        arrayList.add(obj);
    }

    @Override
    public Object pop() {
        return arrayList.remove(arrayList.size() - 1);
    }

    @Override
    public int size() {
        return arrayList.size();
    }
}