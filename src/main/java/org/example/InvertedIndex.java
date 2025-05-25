package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;


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

   public DocListOrdered<Document> searchAndTerm(String query) {
        // List hasil akhir pencarian
        DocListOrdered<Document> result = new DocListOrdered<>();

        // Tokenisasi query input
        String[] tokensQuery = Tokenizer.tokenize(query);

        // Daftar posting list dari setiap token yang cocok
        ArrayList<DocListOrdered<Document>> postingLists = new ArrayList<>();

        // Ambil posting list dari setiap term yang cocok dalam inverted index
        if (tokensQuery.length > 0) {
            for (String token : tokensQuery) {
                for (Term term : this.invertedList) {
                    if (term.getTerm().equals(token)) {
                        postingLists.add(term.getDocOrdered());
                        break; //jika term tidak duplikat, bisa keluar loop lebih cepat
                    }
                }
            }
        }

        // Urutkan posting list dari yang paling kecil 
        postingLists.sort(Comparator.comparingInt(DocListOrdered::size));

        // Jika ada posting list yang ditemukan
        if (!postingLists.isEmpty()) {
            // Ambil posting list pertama sebagai dasar hasil awal
            result = postingLists.get(0);

            // Jika hanya ada satu term/token yang cocok, langsung return
            if (postingLists.size() == 1) {
                return result;
            }else{
                 // Jika lebih dari satu, lakukan intersection antar posting list
                 for (int i = 1; i < postingLists.size(); i++) {
                    result = intersect(result, postingLists.get(i));
                }
            }
        }
        // Jika tidak ada posting list yang ditemukan, return list kosong
        return result;
    }   


    public DocListOrdered<Document> intersect(DocListOrdered<Document> list1, DocListOrdered<Document> list2){
         //membuat docordered linkedlist untuk menyimpan hasil 
         DocListOrdered<Document> result = new DocListOrdered<>();
        
         //inisialisasi iterator pada parameter list1
         ListIterator<Document> iterator1 = list1.listIterator();
         //inisialisasi iterator pada parameter list2
         ListIterator<Document> iterator2 = list2.listIterator();

         //jika iterator 1 dan 2 memiliki data setelah posisi iterator, maka : 
         if(iterator1.hasNext() && iterator2.hasNext()){
          
            //geser iterator ke kanan lalu masukan data ke objek doc1
            Document doc1 = iterator1.next();
            //geser iterator ke kanan lalu masukan data ke objek doc2
            Document doc2 = iterator2.next();
      
            //loop berjalan terus menerus hingga bertemu dengan break
            while (true) {
                //melakukan perbandingan antar dua objek
                int compare = doc1.compareTo(doc2);

                //jka objek doc1 lebih kecil daripada object doc2
                if (compare < 0) {
                    //dan jika setelah iterator pada list1 terdapat data
                    if (iterator1.hasNext()) {
                        //maka geser pointer ke data sebelah lalu masukan data ke dalam objek doc1
                        doc1 = iterator1.next();
                    //jika tidak ada data di sebelah kanan iterator
                    }else{
                        //maka keluar dari loop
                        break;
                    }
                    
                //jka objek doc1 lebih besar daripada object doc2
                }else if(compare > 0){
                     //dan jika setelah iterator pada list2 terdapat data
                    if (iterator2.hasNext()) {
                         //maka geser pointer2 ke data sebelah lalu masukan data ke dalam objek doc2
                        doc2 = iterator2.next();
                    //jika tidak ada data di sebelah kanan iterator
                    }else{
                        //maka keluar dari loop
                        break;
                    }
                //jika objek doc1 dan doc2 sama
                }else {
                    //maka menambahkan data doc tersebut ke doc ordered result
                    result.addSort(doc1);
                       //dan jika setelah iterator pada list1 dan list2 terdapat data
                    if (iterator1.hasNext() && iterator2.hasNext()) {
                        //maka geser pointer ke data sebelah lalu masukan data ke dalam objek doc1
                        doc1 = iterator1.next();
                        //maka geser pointer2 ke data sebelah lalu masukan data ke dalam objek doc2
                        doc2 = iterator2.next();
                    //jika tidak ada data di sebelah kanan iterator
                    } else {
                        //maka keluar dari loop
                        break;
                    }
                }
            }
         }
         return result;
    }
    

    
}
