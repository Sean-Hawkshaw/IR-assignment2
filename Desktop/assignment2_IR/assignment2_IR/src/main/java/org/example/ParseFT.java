package org.example;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ParseFT {

    static ArrayList<Document> Parse_Ft() throws IOException {

        int count = 0;
        int i = 0;
        File[] files2;
        File[] files1 = new File("/Users/seanhawk/Desktop/assignment2_IR/assignment2_IR/Assignment Two/ft").listFiles();
        ArrayList<File> filelist = new ArrayList<>();
        org.jsoup.nodes.Document docFT;

        StringBuilder docNO = new StringBuilder();
        StringBuilder Hl = new StringBuilder();
        StringBuilder txt = new StringBuilder();

        ArrayList<org.apache.lucene.document.Document> ParsedFT = new ArrayList<>();

        for (i = 0; i < files1.length; i++) {
            if (files1[i].isDirectory()) {
                files2 = files1[i].listFiles();


                for (File file : files2) {

                    Collections.addAll(filelist, file);


                }
            }

        }
        System.out.println(filelist.size());
        for (File file : filelist) {
            docFT = Jsoup.parse(file, "UTF-8");
            Elements docs = docFT.getElementsByTag("DOC");
            for(Element doc : docs ) {


                String DocNos = doc.getElementsByTag("DOCNO").text();
                String linksdate = doc.getElementsByTag("DATE").text();
                String linksHL = doc.getElementsByTag("HEADLINE").text();
                String linkstext = doc.getElementsByTag("TEXT").text();

                org.apache.lucene.document.Document document = new org.apache.lucene.document.Document();

                count++;

                document.add(new TextField("DocNo", DocNos.toString(), Field.Store.YES));
                document.add(new TextField("Headline", linksHL.toString(), Field.Store.YES));
                document.add(new TextField("Text", linkstext.toString(), Field.Store.YES));



                ParsedFT.add(document);
            }
            System.out.println("Parsed" + " " +  file.getName());
        }
        System.out.println("Parsing of each File in FT Done");
        return ParsedFT;
    }

}



