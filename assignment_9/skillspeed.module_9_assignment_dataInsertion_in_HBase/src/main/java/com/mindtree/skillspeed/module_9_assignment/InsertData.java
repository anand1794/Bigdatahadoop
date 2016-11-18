package com.mindtree.skillspeed.module_9_assignment;

/**
 * 
 * Module 9 Assignment Hbase data Insertion
 *  
 */
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
/**
 * @author M1035838
 *
 */
public class InsertData{

   public static void main(String[] args) throws IOException {

      // Instantiating Configuration class
      Configuration config = HBaseConfiguration.create();

      // Instantiating HTable class
      HTable hTable = new HTable(config, "employee");

      // Instantiating Put class
      // accepts a row name.
      Put p = new Put(Bytes.toBytes("row4")); 

      // adding values using add() method
      // accepts column family name, qualifier/row name ,value
       p.add(Bytes.toBytes("official"),
      Bytes.toBytes("ID"),Bytes.toBytes("9856"));

      p.add(Bytes.toBytes("official"),
      Bytes.toBytes("designation"),Bytes.toBytes("Human resourse"));

      p.add(Bytes.toBytes("official"),Bytes.toBytes("salary"),
      Bytes.toBytes("700000"));

      p.add(Bytes.toBytes("personal"),Bytes.toBytes("name"),
      Bytes.toBytes("raju chorushiya"));
      
      p.add(Bytes.toBytes("personal"),Bytes.toBytes("city"),
      Bytes.toBytes("gaya"));
      
      // Saving the put Instance to the HTable.
      hTable.put(p);
      System.out.println("data inserted");
      
      // closing HTable
      hTable.close();
   }
}
