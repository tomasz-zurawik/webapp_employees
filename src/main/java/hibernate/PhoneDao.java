package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PhoneDao {

    public void savePhones(Phones phones) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(phones);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Phones> getPhones() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Phones", Phones.class).list();
        }
    }

    public void updatePhones(Phones phones) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(phones);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
