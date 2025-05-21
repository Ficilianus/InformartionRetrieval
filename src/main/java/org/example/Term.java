package org.example;


public class Term implements Comparable<Term>{
    private String term;
    private int df;
    private double idf;

    private DocListOrdered<Document> DocOrdered = new DocListOrdered<>(); 

    public Term(String term){
      this.term = term;
      this.df = 0;
      this.idf = 0;
    }

    public String getTerm() {
      return this.term;
    }
    public void setTerm(String value) {
      this.term = value;
    }

    public int getDf() {
      return this.df;
    }
    public void setDf(int value) {
      this.df = value;
    }

    public double getIdf() {
      return this.idf;
    }
    public void setIdf(double value) {
      this.idf = value;
    }

     public int compareTo(Term otherTerm) {
        return this.term.compareTo(otherTerm.term);
    }

    public DocListOrdered<Document> getDocOrdered() {
      return this.DocOrdered;
    }
    public void setDocOrdered(DocListOrdered<Document> value) {
      this.DocOrdered = value;
    }
}
