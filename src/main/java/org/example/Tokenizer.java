package org.example;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Tokenizer {
    private static List<String> termStopWords = new ArrayList<>();
    //static akan dijalankan saat class dipanggil pertama kali, untuk membaca file stopwords
    static {
        try {
            
            File file = new File("sastrawi");
            termStopWords = Files.readAllLines(Paths.get(file.getAbsolutePath() + "//" + file.list()[0] ));
          
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    

    public static String[] tokenize(String content){
            String[] tokens = content.toLowerCase().split("[^A-Za-z0-9]+");
            List<String> result = new ArrayList<>();
            //  List<String> resultStop = new ArrayList<>();

            for (String token : tokens) {
            // Hapus token yang kosong, angka saja, dan yang masuk ke stopword
            if (!token.isEmpty() && !token.matches("\\d+") && !termStopWords.contains(token)) {
                result.add(token);
            }else{
                //buat debugg

                // System.out.println("ada");
                // resultStop.add(token);
                // return resultStop.toArray(new String[0]);
               }
             
            }
            return result.toArray(new String[0]);
    }
    
     public static List<String> getStopwords() {
        return termStopWords;
    }
}
