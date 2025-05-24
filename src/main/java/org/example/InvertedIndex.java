package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class InvertedIndex {
    DocListOrdered<Term> invertedList = new DocListOrdered<>();

    public void addTerm(Term term) {
        this.invertedList.addSort(term);
    }

    public DocListOrdered<Term> getInvertedList() {
        return this.invertedList;
    }

    public void setInvertedList(DocListOrdered<Term> value) {
        this.invertedList = value;
    }

    public void searchTerm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan Yang ingin dicari: ");
        String keywords = scanner.nextLine();
        String[] kata = keywords.split(" ");
        int jumlahKata = kata.length;
        if (jumlahKata == 2) {
            searchTerm2(keywords);
            return;
        } else if (jumlahKata > 2) {
            System.out.println(" Maksimum 2 kata");
        }
        boolean found = false;
        for (Term term : invertedList) {
            if (term.getTerm().equalsIgnoreCase(keywords)) {
                System.out.println("Term ditemukan: " + term.getTerm());
                System.out.println("Jumlah dokumen yang mengandung term ini: " + term.getDf());
                System.out.println("Daftar dokumen:");
                for (Document doc : term.getDocOrdered()) {
                    System.out.println("- " + doc.getName());
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Term \"" + keywords + "\" tidak ditemukan dalam indeks.");
        }
    }

    public void searchTerm2(String input) {
        // Pisahkan berdasarkan spasi
        String[] keywords = input.split(" ");
        String keyword1 = keywords[0];
        String keyword2 = keywords[1];
        Term term1 = null, term2 = null;
        // Cari Term yang sesuai untuk kedua keyword
        for (Term term : invertedList) {
            if (term.getTerm().equalsIgnoreCase(keyword1)) {
                term1 = term;
            } else if (term.getTerm().equalsIgnoreCase(keyword2)) {
                term2 = term;
            }
        }
        if (term1 == null || term2 == null) {
            System.out.println("Salah satu keyword tidak ditemukan dalam indeks.");
            return;
        }
        // Dapatkan list dokumen dari kedua term
        List<Document> docs1 = term1.getDocOrdered();
        List<Document> docs2 = term2.getDocOrdered();
        int i = 0, j = 0;
        Document d1 = docs1.get(i);
        Document d2 = docs2.get(j);
        boolean found = false;
        // Loop seperti menyamakan dua pointer pada dua linked list terurut
        while (i < docs1.size()) {
            j=0;
            d1 = docs1.get(i);
            d2 = docs2.get(j);
            int comparison = d1.getName().compareTo(d2.getName());
            if (comparison == 0) {
                // Jika nama dokumen sama
                System.out.println("Dokumen ditemukan: " + d1.getName());
                found = true;
                i++;
            } else {
                while (j < docs2.size()) {
                    d2 = docs2.get(j);
                    comparison = d1.getName().compareTo(d2.getName());
                    if (comparison == 0) {
                        // Jika nama dokumen sama
                        System.out.println("Dokumen ditemukan: " + d1.getName());
                        found = true;
                        break;
                    }
                    j++;
                }
                i++;
            }
        }
        if (!found) {
            System.out.println("Tidak ditemukan dokumen yang mengandung kedua keyword.");
        }
    }

    public DocListOrdered<Document> searchAndTerm(String query){
        DocListOrdered<Document> result = new DocListOrdered<>();

        String[] tokensQuery= Tokenizer.tokenize(query);
        // System.out.println(Arrays.toString(tokensQuery));
        ArrayList<DocListOrdered<Document>> postingLists = new ArrayList<>();


        for (Term term : this.invertedList) {
            for (String token : tokensQuery) {
                if (term.getTerm().equals(token)) {
                    postingLists.add(term.getDocOrdered());
                }
            }
        }

        postingLists.sort(Comparator.comparingInt(DocListOrdered::size));
        // for (DocListOrdered<Document> docListOrdered : postingLists) {
        //     System.out.println(docListOrdered.size());
            
        // }

       
      
       

        // for (Document document : intersect(postingLists.get(0) , postingLists.get(1))) {
        //     System.out.println(document.getName());
        // }

      
        result = new DocListOrdered<>();
        result = postingLists.get(0);
        if (postingLists.size() < 2) {
            return result;
        }
        for (int i = 1; i < postingLists.size(); i++) {
            result = intersect(result, postingLists.get(i));
        }

        // // Tampilkan hasil
        // System.out.println("Hasil intersect:");
        // for (Document doc : result) {
        //     System.out.println(doc.getName());
        // }


        return result;
        

        
      
    }

    public DocListOrdered<Document> intersect(DocListOrdered<Document> list1, DocListOrdered<Document> list2){
        DocListOrdered<Document> result = new DocListOrdered<>();
        // System.out.println("Isi list1:");
        // for (Document doc : list1) {
        //     System.out.print(doc.getName() + " ");
        // }

        // System.out.println();

        // System.out.println("Isi list2:");

        // for (Document doc : list2) {
        //     System.out.print(doc.getName() + " ");
        // }

        // System.out.println();

         ListIterator<Document> iterator1 = list1.listIterator();
         ListIterator<Document> iterator2 = list2.listIterator();
         if(iterator1.hasNext() && iterator2.hasNext()){
          
            Document doc1 = iterator1.next();
            Document doc2 = iterator2.next();
      

            while (true) {
                int compare = doc1.compareTo(doc2);
                if (compare < 0) {
                    if (iterator1.hasNext()) {
                        doc1 = iterator1.next();
                    }else{
                        break;
                    }
                  
                  
                }else if(compare > 0){
                    if (iterator2.hasNext()) {
                        doc2 = iterator2.next();
                    }else{
                        break;
                    }
                   
                }else {
                    result.addSort(doc1);
                    if (iterator1.hasNext() && iterator2.hasNext()) {
                        doc1 = iterator1.next();
                        doc2 = iterator2.next();
                    } else {
                        break;
                    }
                }
          
            }
        //    System.out.println(doc1.getName());
         }
        //  System.out.println();
        //  System.out.println("yang ketemu : ");

         return result;
    }
    

    
}
