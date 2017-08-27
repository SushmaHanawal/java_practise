package com.sushma.mongodb.tutorials.mongodb;
import com.mongodb.DB;

import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;



/**
 * Java + MongoDB Hello world Example
 * http://www.mkyong.com/mongodb/java-mongodb-hello-world-example/
 *
 */
public class ConnectToDB {
	  public static void main(String[] args) {
		  try{
				/**** Connect to MongoDB ****/
				// Since 2.10.0, uses MongoClient
				MongoClient mongo = new MongoClient("localhost", 27017);
				
				/**** Get database ****/
				// if database doesn't exists, MongoDB will create it for you
				DB db = mongo.getDB("testdb");
				
				/**** Get collection / table from 'testdb' ****/
				// if collection doesn't exists, MongoDB will create it for you
				DBCollection table = db.getCollection("user");
				
				/**** Insert ****/
				// create a document to store key and value
				BasicDBObject document = new BasicDBObject();
				document.put("name", "Sushma");
				document.put("age", 30);
				document.put("createdDate", new Date());
				table.insert(document);
				
				/**** Find and display ****/
				BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.put("name", "Sushma");
				
				DBCursor cursor = table.find(searchQuery);
				
				while (cursor.hasNext()) {
					System.out.println(cursor.next());
				}
				
				/**** Update ****/
				// search document where name="Sushma" and update it with new values
				BasicDBObject query = new BasicDBObject();
				query.put("name", "Sushma");

				BasicDBObject newDocument = new BasicDBObject();
				newDocument.put("name", "Sushma-updated");

				BasicDBObject updateObj = new BasicDBObject();
				updateObj.put("$set", newDocument);

				table.update(query, updateObj);
				
				/**** Find and display ****/
				BasicDBObject searchQuery2
			    = new BasicDBObject().append("name", "Sushma-updated");
				
				DBCursor cursor2 = table.find(searchQuery2);
				
				while (cursor2.hasNext()) {
					System.out.println(cursor2.next());
				}
				
				/**** Done ****/
				System.out.println("Done");
		  }
		  catch(UnknownHostException e){
			  e.printStackTrace();
			  
		  }
		  catch(MongoException e){
			  e.printStackTrace();
		  }
	  }
}
