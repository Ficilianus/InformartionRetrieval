package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        

        
   
        // Term term1 = new Term("term1");
        // Term term2 = new Term("term2");
        // DocListOrdered<Term> ordered1 = new DocListOrdered<>();
        // ordered1.addSort(term2);
        // ordered1.addSort(term1);
      
        // System.out.println(ordered1.get(1).getTerm());
 
        // ReadFile read1 = new ReadFile("koleksi");
        // read1.read().size();




        //buat testing
        // int index = 49;
//        System.out.println(read1.read().getInvertedList().get(index).getTerm());
//        System.out.println(read1.read().getInvertedList().get(index).getDocOrdered().get(4).getName());
        //akhir testing


        //praktikum

        ReadFile read1 = new ReadFile("koleksi");
        InvertedIndex invertedList = read1.read();
    

        System.out.print("Masukkan Yang ingin dicari: ");
        Scanner input = new Scanner(System.in);
        String query = input.nextLine();

        System.out.println(invertedList.searchAndTerm(query).toString());
        // indexData.searchTerm();  
        MemoryLogger.log();
        input.close();

        // Document Document1 = new Document("doc1");
        // Document Document2 = new Document("doc1");

        // System.out.println(Document1.compareTo(Document2));


       
    }
}
