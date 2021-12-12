package pl.school.configurate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.school.entity.Student;
import pl.school.entity.Teacher;

public class HibernateConfigurator {

    public static SessionFactory SessionFactoryLoader () {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Teacher.class);


        return configuration.buildSessionFactory();
    }

    public static void SessionFactoryLoaderClose (Session session, SessionFactory sessionFactory) {
        session.getTransaction().commit();
        sessionFactory.close();
    }
}
