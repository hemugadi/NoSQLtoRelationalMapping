package com.dataAccess.mongo;

import java.net.UnknownHostException;
import java.util.*;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class MongoCRUD {

	public static MongoClient connect() {
		MongoClient mongo = null;
		try {
			mongo = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return mongo;
	}

	public void getDbs() {
		MongoClient mongoClient;
		List<String> dbs = null;
		mongoClient = connect();
		dbs = mongoClient.getDatabaseNames();
		printList(dbs);
	}
	
	public void getCollections(DB db)
	{
		Set<String> collections = db.getCollectionNames();
		for (String i : collections) {
			//enter code that creates tables here
			System.out.println("This is the collection: "+ i);
			DBCollection coll=db.getCollection(i);
			getIndices(db,coll);
			getDocuments(coll);
		}
	}
	public void getIndices(DB db, DBCollection coll)
	{
	
	}
	
	
	public void getDocuments(DBCollection collection)
	{
		DBCursor cursor = collection.find();
		try {
		   while(cursor.hasNext()) {
		       System.out.println(cursor.next());
		   }
		} finally {
		   cursor.close();
		}
	}
	
	public void printList(List<String> list)	{
		for (String i : list) {
			System.out.println(i);
		}
	}
}
