package com.alucard.springHibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.alucard.springHibernate.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		
		try {
			
			int studentId = 1;			
			
			//get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("Getting student with id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			System.out.println("Delete student..." + myStudent.getFirstName());
			session.delete(myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			//new code
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//delete student with id: 5
			System.out.println("Delete student with id: 5");
			session.createQuery("delete from Student where id=5")
								.executeUpdate();
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done! Phewww..");
			
		} finally {
			factory.close();
		}
	}

}
