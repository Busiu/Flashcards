package com.example.flashcards.utils;

import java.util.ArrayList;
import java.util.HashSet;

public class NoDuplicateArrayList<T> {

    private ArrayList<T> arrayList;
    private HashSet<T> hashSet;

    public NoDuplicateArrayList() {
        this.arrayList = new ArrayList<>();
        this.hashSet = new HashSet<>();
    }

    public boolean add(T e) {
        boolean result = hashSet.add(e);
        if (result) {
            arrayList.add(e);
        }
        return result;
    }

    public T get(int index) {
        return arrayList.get(index);
    }

    public ArrayList<T> getArrayList() {
        return arrayList;
    }

    public void remove(T e) {
        arrayList.remove(e);
        hashSet.remove(e);
    }
}
