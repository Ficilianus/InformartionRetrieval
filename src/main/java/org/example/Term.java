package org.example;
import java.util.LinkedList;
public class Term {
    private LinkedList<Doc> dokumen;
    private String kata;

    public Term(String kata) {
        this.kata = kata;
        this.dokumen = new LinkedList<>();
    }

    public String getKata() {
        return kata;
    }

    public LinkedList<Doc> getDokumen() {
        return dokumen;
    }

    public void tambahDokumen(Doc dokumens) {
        if (!dokumen.contains(dokumens)) {
            dokumen.add(dokumens);
        }
    }

    @Override
    public String toString() {
        return "Kata: '" + kata + "', Muncul di: " + dokumen;
    }

}
