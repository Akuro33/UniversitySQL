package pl.school;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.school.configurate.HibernateConfigurator;

@SpringBootApplication
public class WebApplication {

    private static final Logger LOGGER = LogManager.getLogger(WebApplication.class);

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateConfigurator.SessionFactoryLoader();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();


        SpringApplication.run(WebApplication.class, args);
        HibernateConfigurator.SessionFactoryLoaderClose(session, sessionFactory);
    }
}
