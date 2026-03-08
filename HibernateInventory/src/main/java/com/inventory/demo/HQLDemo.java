package com.inventory.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.inventory.entity.Product;
import com.inventory.loader.ProductDataLoader;
import com.inventory.util.HibernateUtil;

public class HQLDemo {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {

            // ProductDataLoader.loadSampleProducts(session);

            sortProductsByPriceAscending(session);
            sortProductsByPriceDescending(session);
            sortProductsByQuantityDescending(session);

            getFirstThreeProducts(session);

            countTotalProducts(session);
            findMinMaxPrice(session);

        } finally {
            session.close();
            factory.close();
        }
    }

    // SORT PRICE ASC
    public static void sortProductsByPriceAscending(Session session) {

        String hql = "FROM Product p ORDER BY p.price ASC";

        Query<Product> query = session.createQuery(hql, Product.class);
        List<Product> products = query.list();

        System.out.println("\nProducts Sorted by Price ASC");

        for(Product p : products) {
            System.out.println(p);
        }
    }

    // SORT PRICE DESC
    public static void sortProductsByPriceDescending(Session session) {

        String hql = "FROM Product p ORDER BY p.price DESC";

        Query<Product> query = session.createQuery(hql, Product.class);
        List<Product> products = query.list();

        System.out.println("\nProducts Sorted by Price DESC");

        for(Product p : products) {
            System.out.println(p);
        }
    }

    // SORT QUANTITY
    public static void sortProductsByQuantityDescending(Session session) {

        String hql = "FROM Product p ORDER BY p.quantity DESC";

        Query<Product> query = session.createQuery(hql, Product.class);
        List<Product> products = query.list();

        System.out.println("\nProducts Sorted by Quantity");

        for(Product p : products) {
            System.out.println(p.getName() + " Quantity: " + p.getQuantity());
        }
    }

    // PAGINATION
    public static void getFirstThreeProducts(Session session) {

        String hql = "FROM Product p";

        Query<Product> query = session.createQuery(hql, Product.class);

        query.setFirstResult(0);
        query.setMaxResults(3);

        List<Product> products = query.list();

        System.out.println("\nFirst 3 Products");

        for(Product p : products) {
            System.out.println(p);
        }
    }

    // COUNT
    public static void countTotalProducts(Session session) {

        String hql = "SELECT COUNT(p) FROM Product p";

        Query<Long> query = session.createQuery(hql, Long.class);

        Long count = query.uniqueResult();

        System.out.println("\nTotal Products = " + count);
    }

    // MIN MAX PRICE
    public static void findMinMaxPrice(Session session) {

        String hql = "SELECT MIN(p.price), MAX(p.price) FROM Product p";

        Query<Object[]> query = session.createQuery(hql, Object[].class);

        Object[] result = query.uniqueResult();

        System.out.println("\nPrice Range");
        System.out.println("Min Price: " + result[0]);
        System.out.println("Max Price: " + result[1]);
    }
}