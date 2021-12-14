package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import beans.*;
import util.HibernateUtil;


public class VoyageTest {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		System.out.print("hello");
		
		IVoyageImplDAO voyagedao = new IVoyageImplDAO();
		
		try {
// create a student object
			System.out.println("Creating new voyage...");
			Voyage tempuser = new Voyage(1, "madriiid",1000);

			System.out.println("Saving the voyage...");
			voyagedao.saveVoyage(tempuser);


			System.out.println("Done!");
		} finally {
			factory.close();
		}*/
	}

}

