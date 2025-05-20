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

    public void read(){
        //mengecek apakah folder pada path tersebut ada atau tidak
        if (this.folder.exists()) {
            //mengecek apakah folder tersebut merupakan direktori
            if (this.folder.isDirectory()) {
                String[] listDocs = this.folder.list();
                for (String doc : listDocs) {
                   try {
                     Scanner input = new Scanner(new BufferedReader(new FileReader(this.folder.getAbsolutePath() + "//" + doc)));
                     String temp = "";
                     while (input.hasNext()) {
                        temp += input.nextLine();
                     }

                     String[] tokens = Tokenizer.tokenize(temp);
                     
                     for (String token : tokens) {
                        System.out.println(token);
                     }
                   } catch (Exception e) {
                        System.out.println(e);
                   }
                }
            }
        }
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
