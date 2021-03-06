package pl.school;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.school.configurate.HibernateConfigurator;
import pl.school.entity.Student;
import pl.school.quarry.HQLQuarry;
import pl.school.repository.ExampleDate;

import java.util.List;

@SpringBootApplication
public class WebApplication {

    private static final Logger LOGGER = LogManager.getLogger(WebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        SessionFactory sessionFactory = HibernateConfigurator.SessionFactoryLoader();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

/*        ExampleDate exampleDate = new ExampleDate(session);
        exampleDate.checkDB();*/



        LOGGER.info("The application is running");
       HibernateConfigurator.SessionFactoryLoaderClose(session, sessionFactory);

    }
}
