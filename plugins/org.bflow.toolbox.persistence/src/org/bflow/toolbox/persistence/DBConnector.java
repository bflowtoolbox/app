package org.bflow.toolbox.persistence;

import java.net.URL;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBConnector {
	
	private static boolean isInitialized = false;
	
	private static SessionFactory sessionFactory;
	
	private static void initialize() {
		if(isInitialized) return;
		
		Configuration cfg = new Configuration();
		URL cfgFileURL = DBConnector.class.getResource("/hibernate.cfg.xml");
		cfg.configure(cfgFileURL);
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		
		isInitialized = true;
	}

}
