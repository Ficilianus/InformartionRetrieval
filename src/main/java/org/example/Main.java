package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<Term> daftarKata = new LinkedList<>();
        LinkedList<Doc> semuaDokumen = new LinkedList<>();
        //cari folder koleksi dan file txt
        File folder = new File("koleksi");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        //jika tidak menemukan
        if (files == null || files.length == 0) {
            System.out.println("Tidak ada file .txt di folder koleksi.");
            return;
        }
        // Urutkan file berdasarkan angka dalam nama file
        Arrays.sort(files, Comparator.comparing(Main::getFileNameNumber));
        // Baca file dan buat objek Doc
        for (File file : files) {
            try {
                String isi = Files.readString(file.toPath());
                String namaFile = file.getName(); // contoh: doc1.txt
                Doc dokumen = new Doc(isi, namaFile);
                semuaDokumen.add(dokumen);
            } catch (IOException e) {
                System.out.println("Gagal membaca file: " + file.getName());
            }
        }
        // Proses setiap dokumen untuk membuat daftar kata (jadikan huuruf kecil)
        for (Doc dok : semuaDokumen) {
            String[] kataKata = dok.getKata().toLowerCase().split("\\s+");
            for (String kata : kataKata) {
                Term kataObjek = cariAtauTambahKata(daftarKata, kata);
                kataObjek.tambahDokumen(dok);
            }
        }
        // Cetak daftar semua term
        for (Term term : daftarKata) {
            System.out.println(term);
        }
        // search maks 2 kata
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nMasukkan kata yang ingin di cari (maks 2 kata) : ");
            String input = scanner.nextLine().toLowerCase();
            //exit untuk berhenti perulangan (opsional aja)
//            if (input.equals("exit")) {
//                System.out.println("Program selesai.");
//                break;
//            }
            String[] kataDicari = input.split("\\s+");
            if (kataDicari.length == 1) {
                // Pencarian 1 kata
                Term term = null;
                for (Term t : daftarKata) {
                    if (t.getKata().equals(kataDicari[0])) {
                        term = t;
                        break;
                    }
                }
                if (term == null) {
                    System.out.println("Kata '" + kataDicari[0] + "' tidak ditemukan di dokumen manapun.");
                } else {
                    System.out.println("Kata '" + kataDicari[0] + "' ditemukan di dokumen:");
                    for (Doc d : term.getDokumen()) {
                        System.out.println("- " + d.getNamaDoc().replace(".txt", ""));
                    }
                }

            } else if (kataDicari.length == 2) {
                // Pencarian 2 kata (interseksi)
                Term term1 = null, term2 = null;
                for (Term t : daftarKata) {
                    if (t.getKata().equals(kataDicari[0])) {
                        term1 = t;
                    } else if (t.getKata().equals(kataDicari[1])) {
                        term2 = t;
                    }
                }
                if (term1 == null || term2 == null) {
                    System.out.println("Salah satu atau kedua kata tidak ditemukan.");
                    continue;
                }
                Set<String> dokumen1 = new HashSet<>();
                for (Doc d : term1.getDokumen()) {
                    dokumen1.add(d.getNamaDoc());
                }

                List<String> hasil = new ArrayList<>();
                for (Doc d : term2.getDokumen()) {
                    if (dokumen1.contains(d.getNamaDoc())) {
                        hasil.add(d.getNamaDoc().replace(".txt", ""));
                    }
                }

                if (hasil.isEmpty()) {
                    System.out.println("Tidak ada dokumen yang mengandung kedua kata tersebut.");
                } else {
                    System.out.println("Dokumen yang mengandung kedua kata '" + kataDicari[0] + "' dan '" + kataDicari[1] + "':");
                    for (String docName : hasil) {
                        System.out.println("- " + docName);
                    }
                }

            } else {
                System.out.println("Harap masukkan hanya 1 atau 2 kata.");
            }
        }



    }

    // Ambil angka dari nama file, misalnya: doc12.txt → 12
    private static int getFileNameNumber(File file) {
        String name = file.getName().replace(".txt", "").toLowerCase();
        String angka = name.replaceAll("[^0-9]", "");
        try {
            return Integer.parseInt(angka);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Tambahkan term baru jika belum ada
    public static Term cariAtauTambahKata(LinkedList<Term> daftar, String isiKata) {
        for (Term term : daftar) {
            if (term.getKata().equals(isiKata)) {
                return term;
            }
        }
        Term baru = new Term(isiKata);
        daftar.add(baru);
        return baru;
    }
}
