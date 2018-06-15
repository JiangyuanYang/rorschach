package com.wxun.proxy.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @author Zhuwx
 * @since 2018-06-14 13:23
 */
public class MongoUtil {
	private static MongoCollection<Document> proxy_ip;

	static {
		MongoClient mongoClient = new MongoClient("localhost", 27017);

		// 连接到数据库
		MongoDatabase mongoDatabase = mongoClient.getDatabase("proxy");
		System.out.println("Connect to database successfully");
		proxy_ip = mongoDatabase.getCollection("proxy_ip");
	}

	public static void insert(String str) {
		try {
			Document document = Document.parse(str);
			proxy_ip.insertOne(document);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
