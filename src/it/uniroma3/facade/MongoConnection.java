package it.uniroma3.facade;


import com.mongodb.MongoClient;

/*public class MongoConnection {

	private MongoClient mongoClient;

	public MongoConnection () {
		this.mongoClient = new MongoClient ("localhost", 27017 );
	}

	public MongoClient getMongoClient() {
		return this.mongoClient;
	}

	public void close() {
		this.mongoClient.close();
	}
}*/

public class MongoConnection {

	private static MongoConnection instance;
	private MongoClient mongoClient;

	private MongoConnection() {
		this.mongoClient = new MongoClient ("localhost", 27017 );
	}

	public static MongoConnection getInstance()
	{
		if (instance == null)
		{
			instance = new MongoConnection();
		}

		return instance; 
	}
	
	public MongoClient getMongoClient() {
		return this.mongoClient;
	}
	
	public void close() {
		instance = null;
		this.mongoClient.close();
	}
}
