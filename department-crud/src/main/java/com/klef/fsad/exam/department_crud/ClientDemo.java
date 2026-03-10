package com.klef.fsad.exam.department_crud;

import java.util.Date;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        Configuration cfg = new Configuration();
        cfg.configure();

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        Scanner sc = new Scanner(System.in);


        Transaction tx = session.beginTransaction();

        Department dept = new Department(
                "CSE",
                "Computer Science Department",
                new Date(),
                "Active"
        );

        session.save(dept);

        System.out.println("Department Inserted Successfully");

        tx.commit();

        System.out.print("Enter Department ID to delete: ");
        int id = sc.nextInt();

        Transaction tx2 = session.beginTransaction();

        Department d = session.get(Department.class, id);

        if(d != null)
        {
            session.delete(d);
            System.out.println("Department Deleted Successfully");
        }
        else
        {
            System.out.println("Department ID not found");
        }

        tx2.commit();

        session.close();
        factory.close();
        sc.close();
    }
}