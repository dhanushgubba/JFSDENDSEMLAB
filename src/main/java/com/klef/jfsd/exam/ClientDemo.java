package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            
            int departmentId = 1; 
            String newName = "Research & Development";
            String newLocation = "Hyderabad";

            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE id = ?3";
            int updated = session.createQuery(hql)
                                 .setParameter(1, newName)
                                 .setParameter(2, newLocation)
                                 .setParameter(3, departmentId)
                                 .executeUpdate();

            if (updated > 0) {
                System.out.println("Department details updated successfully!");
            } else {
                System.out.println("No department found with the given ID.");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
