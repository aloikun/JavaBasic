package com.loikun;

import com.loikun.domain.Book;

import java.util.HashMap;

/**
 * hashMap 的使用场景
 */
public class MyMap {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        Book javaBook = new Book("123", "Java",46.5f);
        Book dbBook = new Book("456", "DB",50.5f);
        map.put(javaBook.getJSBN(), javaBook);
        map.put(dbBook.getJSBN(), dbBook);
        Book book;
        if (map.containsKey("123")){
            book = (Book) map.get("123");
            System.out.println(book.getName()+","+book.getPrice());
        }
    }
}
