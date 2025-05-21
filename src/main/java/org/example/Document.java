package org.example;

public class Document implements Comparable<Document> {
    private String name;

    public Document(String name){
        this.name = name;
    }

    public String getName() {
      return this.name;
    }
    public void setName(String value) {
      this.name = value;
    }

     public int compareTo(Document otherDoc) {
        //mengambil index huruf "c"
       int index1 = this.name.lastIndexOf("c");
       int index2 = otherDoc.name.lastIndexOf("c");

      //mengambil angka setelah huruf c
      String name1 = this.name.substring(index1+1);
      String name2 = otherDoc.getName().substring(index2+1);

      //mengubah angka yang telah diambil ke integer
      int angkaName1 = Integer.parseInt(name1);
      int angkaName2 = Integer.parseInt(name2);

      return Integer.compare(angkaName1, angkaName2);
    }
}
