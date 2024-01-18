package org.example;

import org.example.model.Customer;
import org.example.model.Order;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class AppMain {
    public static void main(String[] args) {

        Customer customer=new Customer();
        customer.setFirstName("Filiz");
        customer.setLastName("Kanar");

        Order order=new Order("Radio","1200",12542.50f,new Date());
        Order order1=new Order("Defter","1300",250.50f,new Date());
        Order order2=new Order("Cetvel","1500",210.45f,new Date());

        customer.getOrders().add(order);
        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        order.setCustomer(customer);
        order1.setCustomer(customer);
        order2.setCustomer(customer);

        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;

        try {
            transaction=session.beginTransaction();
            session.save(customer);
            session.save(order);
            session.save(order1);
            session.save(order2);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            System.out.println("Hata: "+e);
        }finally {
            session.close();
        }


    }
}