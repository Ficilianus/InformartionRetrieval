package org.example;

public class Document implements Comparable<Document> {
    private String name;

    public String getName() {
      return this.name;
    }
    public void setName(String value) {
      this.name = value;
    }

     public int compareTo(Document otherDoc) {
        return this.name.compareTo(otherDoc.name);
    }
}
