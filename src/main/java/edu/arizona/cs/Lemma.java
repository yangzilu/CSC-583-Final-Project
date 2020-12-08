/**
 * It is only used for lemmatize the wiki files. Lemmatize 80 files 
 * takes quite large amount of time, so I put it in a separate class.
 * It takes couples of hours. 
 */

package edu.arizona.cs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import edu.stanford.nlp.simple.*;

public class Lemma implements Runnable {

    int id;

    public Lemma(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("===> Thread " + id + ": running...");

        File dir = new File("src/main/resources/wiki/");
        File dirList[] = dir.listFiles();

        for (int i = 0; i < 10; i++) {
            File file = dirList[id * 10 + i];
            System.out.println("===> Thread " + id + ": lemmatizing file: " + file.getName() + "...");
            lemmatize(file);
        }

        System.out.println("===> Thread " + id + ": done!");
    }

    public static void main(String[] args) {

        int n = 8;
        for (int i = 0; i < n; i++) {
            Thread th = new Thread(new Lemma(i));
            th.start();
        }
    }

    private static void lemmatize(File file) {
        try {
            File dir = new File("src/main/resources/lemma_wiki2/");
            if (!dir.exists())
                dir.mkdirs();

            File lemma_file = new File("src/main/resources/lemma_wiki2/lemma_" + file.getName());
            if (lemma_file.createNewFile()) {
                System.out.println("file create");
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(lemma_file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() == 0) {
                    writer.write(System.lineSeparator());
                } else if (line.matches("\\[\\[.*\\]\\]")) {
                    writer.write(line + System.lineSeparator());
                } else {
                    String lemma_line = "";
                    Document doc = new Document(line);
                    for (Sentence sent : doc.sentences()) {
                        lemma_line += String.join(" ", sent.lemmas());
                    }
                    writer.write(lemma_line + System.lineSeparator());
                }

            }

            reader.close();
            writer.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
