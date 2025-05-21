package org.example;

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
}
