package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihan = 0;

        while (pilihan != 3) {
            System.out.println("=== MENU UTAMA ===");
            System.out.println("1. Pencarian");
            System.out.println("2. Tampilkan Inverted Index");
            System.out.println("3. Keluar");
            System.out.print("Masukkan pilihan Anda (1-3): ");

            pilihan = scanner.nextInt();
            scanner.nextLine(); 

            if (pilihan == 1) {
                System.out.println("Anda memilih: Pencarian");

               
                ReadFile read1 = new ReadFile("koleksi");
                InvertedIndex invertedList = read1.read();

                System.out.print("Masukkan kata yang ingin dicari: ");
                String query = scanner.nextLine();
                System.out.print("Dokumen yang mengandung kata tersebut: ");
                System.out.println(invertedList.searchAndTerm(query).toString());

                MemoryLogger.log();
                pause(scanner);

            } else if (pilihan == 2) {
                System.out.println("Anda memilih: Tampilkan Inverted Index");

                ReadFile read1 = new ReadFile("koleksi");
                InvertedIndex invertedList = read1.read();

                System.out.println("Daftar Inverted Index:");
                for (Term term : invertedList.getInvertedList()) {
                    System.out.print(term.getTerm() + ": ");
                    for (Document doc : term.getDocOrdered()) {
                        System.out.print(doc.getName() + " ");
                    }
                    System.out.println();
                }

                pause(scanner);

            } else if (pilihan == 3) {
                System.out.println("Terima kasih! Program selesai.");
            } else {
                System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-3.");
                pause(scanner);
            }

            System.out.println(); 
        }

        scanner.close();
    }

    private static void pause(Scanner scanner) {
        System.out.println("\nTekan Enter untuk kembali ke menu...");
        scanner.nextLine(); 
    }
}
