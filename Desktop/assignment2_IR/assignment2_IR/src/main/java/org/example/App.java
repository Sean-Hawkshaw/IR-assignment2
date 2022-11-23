package org.example;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.snowball.SnowballAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.search.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
// import org.apache.lucene.store.RAMDirectory;
import org.jsoup.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.ls.LSOutput;


import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;




public class App {
    public App() throws IOException {
    }


//    static ArrayList<org.apache.lucene.document.Document> Parse_Ft() throws IOException {
//
//        int count = 0;
//        int i = 0;
//        File[] files2;
//        File[] files1 = new File("/Users/seanhawk/Desktop/assignment2_IR/assignment2_IR/Assignment Two/ft").listFiles();
//        ArrayList<File> filelist = new ArrayList<>();
//        Document docFT;
////        ArrayList<Element> docno = new ArrayList<>();
////       // ArrayList<Element> date = new ArrayList<>();
////        ArrayList<Element> HL = new ArrayList<>();
////        ArrayList<Element> Text = new ArrayList<>();
//
//
//        StringBuilder docNO = new StringBuilder();
//        StringBuilder Hl = new StringBuilder();
//        StringBuilder txt = new StringBuilder();
//
//        ArrayList<org.apache.lucene.document.Document> ParsedFT = new ArrayList<>();
//
//        for (i = 0; i < files1.length; i++) {
//            if (files1[i].isDirectory()) {
//                files2 = files1[i].listFiles();
//
//
//                for (File file : files2) {
//
//                    Collections.addAll(filelist, file);
//
//
//                }
//            }
//
//        }
//        System.out.println(filelist.size());
//        for (File file : filelist) {
//            docFT = Jsoup.parse(file, "UTF-8");
//
////            Element content = doc.getElementById("");
//
//
//            Elements docs = docFT.getElementsByTag("DOC");
//
//
//
//            for(Element doc : docs ) {
//
//
//                String DocNos = doc.getElementsByTag("DOCNO").text();
//                String linksdate = doc.getElementsByTag("DATE").text();
//                String linksHL = doc.getElementsByTag("HEADLINE").text();
//                String linkstext = doc.getElementsByTag("TEXT").text();
////            for (String docno : DocNos) {
////                docNO = docNO.append(docno);
////            }
//////            for (Element linkdate : linksdate) {
//////                date.add(linkdate);
//////            }
////            for (String HL : linksHL) {
////                Hl = Hl.append(HL);
////            }
////            for (String text : linkstext) {
////                txt = txt.append(text);
////            }
//
////            for (int j = 0; j < Text.size(); j++) {
//                org.apache.lucene.document.Document document = new org.apache.lucene.document.Document();
//
//                count++;
//
////                document.add(new TextField("DocNo", (docno.get(j)).toString(), Field.Store.YES));
////                document.add(new TextField("Date", date.get(j).toString(), Field.Store.YES));
//////                document.add(new TextField("Headline", HL.get(j).toString(), Field.Store.YES));
////                document.add(new TextField("Text", Text.get(j).toString(), Field.Store.YES));
//                document.add(new TextField("DocNo", DocNos.toString(), Field.Store.YES));
////            document.add(new TextField("Date", l.toString(), Field.Store.YES));
//                document.add(new TextField("Headline", linksHL.toString(), Field.Store.YES));
//                document.add(new TextField("Text", linkstext.toString(), Field.Store.YES));
//
////               iwr.addDocument(document); //adding document to the index
////
////                            break;
////                        }
////                       break;
////                    }
////                    break;
////                }
////                continue;
////            }
////
//                ParsedFT.add(document);
////                System.out.println("File " + count + " parsed");
//            }
//            System.out.println("Parsed" + " " +  file.getName());
//       }
//        System.out.println("Parsing of each File in FT Done");
//    return ParsedFT;
//    }


    private static String INDEX_DIRECTORY = "../index";

//    public static void searchText() throws Exception {
//
//        BM25Similarity BM25similarity = new BM25Similarity();
//        ClassicSimilarity clsimilarity = new ClassicSimilarity();
//        Directory directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY)); //directory where in index will be stored in memory
//        IndexReader reader = DirectoryReader.open(directory); //initialising the index reader
//        IndexSearcher searcher = new IndexSearcher(reader); //initialising the index searcher
//        //searcher.setSimilarity(BM25similarity); //setting similarity of searcher
//        searcher.setSimilarity(clsimilarity);
//
//        File file2 = new File("/Users/seanhawk/Desktop/assignment2_IR/assignment2_IR/mycranrelTEXT.txt"); //new path to store the scores of the docs and qrs
//        FileOutputStream fout = new FileOutputStream(file2);
//
//        ArrayList<String> queries = SearchFiles.CreateQueries("/Users/seanhawk/Desktop/assignment2_IR/assignment2_IR/topics");
//        ArrayList<Integer> queryID = SearchFiles.CreateIds("/Users/seanhawk/Desktop/assignment2_IR/assignment2_IR/topics");
//        QueryParser qp = new QueryParser("text", new StandardAnalyzer() ); //initialising a query parser and giving it an analyser and the fiels we wish to search
//
//
//
//        Query query = null;
//        int indx = 0;
//        ArrayList<Query> pqueries = new ArrayList<>(); //query list containing the parsed queries using query parser
//        TopDocs hits = null;
//        String s = "";
//        int x = 0;
//
//        for (int i = 0; i < queries.size(); i++) {
//            query = qp.parse(queries.get(i)); //parsing queries from queries array
//            pqueries.add(query); //adding them to querys list
//        }
//
//        for (indx = 1; indx < queries.size(); indx++) {
//            hits = searcher.search(pqueries.get(indx), 1000);//searching the info field using the parsed queries and storing the top 50 results
//            for (ScoreDoc sd : hits.scoreDocs) { //accessing the scoreDocs field using the ScoreDoc object
//                x ++;
//                Document d = searcher.doc(sd.doc); //storing the topdoc fields in a document
//                s = queryID.get(indx) + " " + "0" + " " + d.get("docno") + " " + x +  " " + sd.score + " " + "STANDARD" + "\n"; //pint results that are needed for trec_eval
//
//            }
//
//        }
//
//        PrintWriter pw = new PrintWriter(fout);
//        pw.write(s);
//        pw.flush();
//        fout.close();
//        pw.close();
//
//        reader.close();
//        directory.close();
//
//
//    }

    public static void main (String[]args ) throws Exception {

        Analyzer analyzer = new StandardAnalyzer();
        ClassicSimilarity clsimilarity = new ClassicSimilarity();
        Directory directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY)); //directory where in index will be stored in memory
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setSimilarity(clsimilarity);
        IndexWriter iwriter = new IndexWriter(directory, config);

        String FT_DIRECTORY_PATH = "/Users/seanhawk/Desktop/assignment2_IR/assignment2_IR/Assignment Two/ft";
        String FBIS_DIRECTORY_PATH = "/Users/seanhawk/Desktop/assignment2_IR/assignment2_IR/Assignment Two/fbis";
        String LATIMES_DIRECTORY_PATH = "/Users/seanhawk/Desktop/assignment2_IR/assignment2_IR/Assignment Two/latimes";
        String FEDREGISTER_DIRECTORY_PATH = "/Users/seanhawk/Desktop/assignment2_IR/assignment2_IR/Assignment Two/fr94";

        ArrayList<Document> documentsFT = ParsingDocuments.parseFT(FT_DIRECTORY_PATH);
 //       ArrayList<Document> documentsFBIS = ParsingDocuments.parseFBIS(FBIS_DIRECTORY_PATH);
//        ArrayList<Document> documentsLATIMES = ParsingDocuments.parseLATimes(LATIMES_DIRECTORY_PATH);
//        ArrayList<Document> documentsFEDREGISTER = ParsingDocuments. parseFedRegister(FEDREGISTER_DIRECTORY_PATH);


        Create_Index.Make_Index(documentsFT, iwriter);
//        Create_Index.Make_Index(documentsFBIS, iwriter);
//        Create_Index.Make_Index(documentsLATIMES, iwriter);
//        Create_Index.Make_Index(documentsFEDREGISTER, iwriter);//


        iwriter.close();

        search_Index.searchText();
        directory.close();

        }

}
