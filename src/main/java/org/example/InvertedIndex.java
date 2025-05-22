package org.example;
import java.util.List;
import java.util.Scanner;
public class InvertedIndex {
    DocListOrdered<Term> invertedList = new DocListOrdered<>();

      public void addTerm(Term term){
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
          if(jumlahKata == 2) {
              searchTerm2(keywords);
              return;
          }else if(jumlahKata > 2){
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
        boolean found = false;
        // Loop seperti menyamakan dua pointer pada dua linked list terurut
        while (i < docs1.size() && j < docs2.size()) {
            Document d1 = docs1.get(i);
            Document d2 = docs2.get(j);

            int comparison = d1.getName().compareTo(d2.getName());

            if (comparison == 0) {
                // Jika nama dokumen sama
                System.out.println("Dokumen ditemukan: " + d1.getName());
                found = true;
                i++;
                j++;
            } else if (comparison < 0) {
                i++; // majukan pointer dari docs1
            } else {
                j++; // majukan pointer dari docs2
            }
        }
        if (!found) {
            System.out.println("Tidak ditemukan dokumen yang mengandung kedua keyword.");
        }
    }

}
