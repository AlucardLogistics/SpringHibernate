package com.alucard.springHibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.alucard.springHibernate.entity.Student;
import com.alucard.springHibernate.utils.DateUtils;

public class CreateStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		
		try {
			//create a student object
			System.out.println("Creating a new student object...");
			
			String theDateOfBirthStr = "31/12/1989";
			 
            Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
			
			Student tempStudent = new Student("Dragos", "Stoian", "sadics@live.com", theDateOfBirth);
			
			//start  a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student...");
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done! Phewww..");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}

}
