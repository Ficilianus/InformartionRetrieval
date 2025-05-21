package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class ReadFile {
    private String path;
    private File folder;

    public ReadFile(String path){
        this.path = path;
        this.folder = new File(path);
    }

    public InvertedIndex read(){
        InvertedIndex invertedTermList = new  InvertedIndex();
        //mengecek apakah folder pada path tersebut ada atau tidak
        if (this.folder.exists()) {
            //mengecek apakah folder tersebut merupakan direktori
            if (this.folder.isDirectory()) {
                String[] listDocs = this.folder.list();
                //perulangan setiap docs
                for (String doc : listDocs) {
                   try {
                    //deklarasi scanner untuk membacanya dari buffer (memory) terhadap setiap path (lokasi) dokumen
                     Scanner input = new Scanner(new BufferedReader(new FileReader(this.folder.getAbsolutePath() + "//" + doc)));
                     String temp = "";
                     //untuk mengambil nama document tanpa ekstensi file
                     doc =  doc.substring(0,doc.lastIndexOf('.')).toLowerCase();
                     //selama masih ada token
                     while (input.hasNext()) {
                        //ambil satu baris lalu disimpan ke variabel temp
                        temp += input.nextLine();
                     }
                     //melakukan tokenize dari class tokenizer yang sudah melakukan filtering stopword dan memasukanya ke dalam array tokens
                     String[] tokens = Tokenizer.tokenize(temp);
                     
                     //melakukan perulangan untuk setiap token
                     for (String token : tokens) {
                        //membuat objek document baru
                        Document documentObject = new Document(doc);
                        //melakukan perulangan pada inverted term liist 
                        if (!invertedTermList.getInvertedList().isEmpty()) {
                          for (Term term : invertedTermList.getInvertedList()) {
                            //jika token dan term sama maka  menambahkan document saja
                            if (token.equals(term.getTerm())) {
                                term.getDocOrdered().addSort(documentObject);
                                term.setDf(term.getDocOrdered().size());
                                break;
                            }
                          }
                        }
                        Term termObject = new Term(token);
                          // System.out.println(termObject.getTerm());
                        termObject.getDocOrdered().addSort(documentObject);
                        invertedTermList.addTerm(termObject);
                        input.close();
                     }
                   } catch (Exception e) {
                        System.out.println(e);
                   }
                   
                }
            }
          }
        return invertedTermList;
    }


    public String getPath() {
      return this.path;
    }
    public void setPath(String value) {
      this.path = value;
    }

    public File getFolder() {
      return this.folder;
    }
    public void setFolder(File value) {
      this.folder = value;
    }
}
