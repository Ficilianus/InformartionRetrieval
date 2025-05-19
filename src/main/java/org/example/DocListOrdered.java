package org.example;
import java.util.LinkedList;
import java.util.ListIterator;

public class DocListOrdered<T extends Comparable > extends LinkedList<T> {
    public boolean addSort(T objek){
        ListIterator<T> iterator = this.listIterator();
        return true;
    }
}
