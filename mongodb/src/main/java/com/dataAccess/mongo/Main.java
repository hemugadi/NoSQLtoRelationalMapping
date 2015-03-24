package com.dataAccess.mongo;

import java.net.UnknownHostException;
import java.util.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class Main {
	public static DB getdb(String database) {
		DB db = null;
		MongoClient mongo;
		try {
			mongo = new MongoClient("localhost", 27017);
			db = mongo.getDB(database);
	
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return db;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		MongoCRUD crud=new MongoCRUD();
		
		System.out.println("Here are a list of databases: ");
		crud.getDbs();
		//Get DB name from user
		System.out.println("Enter Name of DB you want to scan: ");
		String dbName=in.nextLine();		
		DB db = getdb(dbName);
		System.out.println("The collections in this db are: ");
		crud.getCollections(db);
		
		 
		System.out.println("Done");
		in.close();
	}
}
