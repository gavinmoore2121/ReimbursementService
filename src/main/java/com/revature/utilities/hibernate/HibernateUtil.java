package com.revature.utilities.hibernate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * A collection of utilities for using the Hibernate API. Contains methods to easily 
 * configure a singleton SessionFactory and hide sensitive information.
 * 
 * A DatabaseDetails.info file can be placed in the src/main/resources folder, containing the database URL,
 * user name, and password to avoid having these details directly in the Hibernate configuration file. 
 */
public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	/**
	 * Initialize the singleton SessionFactory if necessary, otherwise return it.
	 * @return the single SessionFactory, configured according to the hibernate.cfg.xml file.
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			initSessionFactoryFromEnv();
		}
		return sessionFactory;
	}
	
	/**
	 * Instantiate and configure the SessionFactory object.
	 */
	private static void initSessionFactoryFromEnv() {
		// Configure base details from hibernate.cfg.xml
		StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder().configure();

		// If file exists, configure URL, username, and password from DatabaseDetails.info.
		try (BufferedReader databaseDetailReader = getDatabaseDetailsFileReader()) {
			String url = databaseDetailReader.readLine();
			String username = databaseDetailReader.readLine();
			String password = databaseDetailReader.readLine();
			
			registryBuilder.applySetting("hibernate.connection.url", url);
			registryBuilder.applySetting("hibernate.connection.username", username);
			registryBuilder.applySetting("hibernate.connection.password", password);
		} catch (IOException e) {
			/*
			 * Indicates DatabaseDetails.info file was not read or improperly formatted. Configure account details
			 * from hibernate.cfg.xml if possible.
			 */
			e.printStackTrace();
		}

		// Complete SessionFactory instantiation.
		MetadataSources sources = new MetadataSources(registryBuilder.build());
		Metadata metadata = sources.getMetadataBuilder().build();
		sessionFactory = metadata.getSessionFactoryBuilder().build();
	}
	
	/**
	 * Validate the location of the DatabaseDetails.info file and create a reader for it.
	 * @return BufferedReader to read the DatabaseDetails.info file.
	 * @throws FileNotFoundException when DatabaseDetails.info is not correctly located.
	 */
	private static BufferedReader getDatabaseDetailsFileReader() throws FileNotFoundException {
		final String FILENAME = "DatabaseDetails.info";
		
		File accountDetails = new File(FILENAME);
		
		if (!accountDetails.isFile()) {
			throw new FileNotFoundException("DatabaseDetails.info file not found in root folder. If URL, account"
					+ "name, and password are directly in the hibernate.cfg.xml folder, configuration will continue.");
		}
		
		return new BufferedReader(new FileReader(accountDetails));
		
	}
}
