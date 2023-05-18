package com.nagarro.Hibernate;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;





public class ProductView extends Thread{



   private final String FOLDERNAME = "csv_file";
    private final AtomicBoolean RUNNING;
    private List<String> filesList;
    private ProductDetails productDetails;



   public ProductView()
    {
        productDetails = new ProductDetails();
        RUNNING = new AtomicBoolean(true);
    }



   public void stopThread()
    {
        RUNNING.set(false);
    }



   @Override
    public void run() {
        while(RUNNING.get()){
            try {
                filesList = getFilesList(FOLDERNAME);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted, Failed to complete operation");
            }
        }
    }



   public void readFile(String color,String size,String gender,int choice){



       // Create an object of file reader
        // class with CSV file as a parameter.
        
        updateDatabase();
        List<Shirt> tshirtsList;



       String order;
        if(choice==1)
        {
            order = "t.price";
        }
        else if(choice==2)
        {
            order = "t.rating";
        }
        else
        {
            order = "t.price, t.rating";
        }
        
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Shirt.class).buildSessionFactory();



       Session session = factory.openSession();
        session.beginTransaction();
        
        String hql = "from Shirt t WHERE t.colour = :color and t.gender = :gender and t.size = :size order by "+order;
        Query query = session.createQuery(hql);
        query.setParameter("color",color);
        query.setParameter("gender", gender);
        query.setParameter("size", size);
        
        tshirtsList = query.list();
        
        session.getTransaction().commit();
        session.close();
        
        
        productDetails.viewTShirt(tshirtsList);
    }



   private void updateDatabase()
    {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Shirt.class).buildSessionFactory();



       Session session = factory.openSession();
        session.beginTransaction();
        
        for (String i:
            filesList) {



       try {
            java.io.FileReader filereader = new java.io.FileReader(FOLDERNAME+"\\" + i);



           CSVParser parser = new CSVParserBuilder().withSeparator('|')
                    .build();



           // create csvReader object with
            
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();



           List<String[]> allData = csvReader.readAll();



           // print Data
            for (String[] row : allData) {
                    Shirt tshirt = new Shirt(row[0],row[1],row[2],
                    		row[3],row[4],Float.parseFloat(row[5]),Float.parseFloat(row[6]),row[7]);
                    session.merge(tshirt);
            }



       } catch (CsvException | IOException e) {
            System.out.println(e);
        }
    }
        session.getTransaction().commit();
        session.close();
    }
    
    private List<String> getFilesList(String folderName) {



       List<String> files = new ArrayList<>();
        File folder = new File(folderName);
        for (final File fileEntry : folder.listFiles()) {
            if(fileEntry.getName().contains(".csv")) {
                files.add(fileEntry.getName());
            }
        }



       return files;
    }
}