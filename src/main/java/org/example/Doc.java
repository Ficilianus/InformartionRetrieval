package org.example;

public class Doc {
    private String kata;
    private String namaDoc;

    public Doc(String kata, String namaDoc) {
        this.kata = kata;
        this.namaDoc = namaDoc;
    }

    public String getKata() {
        return kata;
    }

    public String getNamaDoc() {
        return namaDoc;
    }

    @Override
    public String toString() {
        return namaDoc;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Doc doc = (Doc) obj;
        return namaDoc.equals(doc.namaDoc);
    }

    @Override
    public int hashCode() {
        return namaDoc.hashCode();
    }
}