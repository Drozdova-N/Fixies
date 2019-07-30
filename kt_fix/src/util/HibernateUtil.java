package util;

import javafx.scene.control.Alert;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();

        } catch (Throwable throwable) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Ошибка подключения к бд");
            alert.show();
            throw new ExceptionInInitializerError(throwable);

        }
    }

    public static SessionFactory getSession() {
        return sessionFactory;
    }
}