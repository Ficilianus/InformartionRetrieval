package org.example;
import java.util.LinkedList;
import java.util.ListIterator;

public class DocListOrdered<T extends Comparable<T> > extends LinkedList<T> {
    public boolean addSort(T objek){
        ListIterator<T> iterator = this.listIterator();
        //selama di depan iterator masih ada objek maka loop
        while (iterator.hasNext()) {
            T temp = iterator.next();
            // kalau ada objek yang sama maka akan return false
            if (temp.compareTo(objek) == 0) {
                return false;
            // kalau objek temp lebih besar dari objek parameter
            }else if (temp.compareTo(objek) > 0) {
                //iterator mundur satu langkah ke belakang
                iterator.previous();
                //tambah objek baru
                iterator.add(objek);
                return true;
            }
        }
        iterator.add(objek);
        return true;
    }
}
