package om.alucard.springHibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.alucard.springHibernate.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		
		try {
			//create 3 student objects
			System.out.println("Creating 3 student objects...");
			Student tempStudent1 = new Student("Bruce", "Wayne", "bruceW@Gotham.com");
			Student tempStudent2 = new Student("Clark", "Kent", "clarkK@dailyPlanet.com");
			Student tempStudent3 = new Student("Diana", "Prince", "DianaP@temiskera.com");
			
			//start  a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done! Phewww..");
			
		} finally {
			factory.close();
		}
	}

}
