package org.example;

public class Main {
    public static void main(String[] args) {
   
        Term term1 = new Term("term1");
        Term term2 = new Term("term2");
        DocListOrdered<Term> ordered1 = new DocListOrdered<>();
        ordered1.addSort(term2);
        ordered1.addSort(term1);
      
        System.out.println(ordered1.get(1).getTerm());
    }
}
