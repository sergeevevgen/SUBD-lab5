package logic.my;

import models.Material;
import models.my.Company;
import models.my.Object;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class ObjectLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Insert 1 to create object");
        System.out.println("Insert 2 to read object");
        System.out.println("Insert 3 to  update object");
        System.out.println("Insert 4 to delete object");
        System.out.println("Insert 5 to filter");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        switch (i) {
            case 1 -> create(session);
            case 2 -> read(session);
            case 3 -> update(session);
            case 4 -> delete(session);
            case 5 -> filterRead(session);
        }
        session.getTransaction().commit();
    }

    private void create(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert object address");
        String address = scanner.next();
        System.out.println("Insert object object_type");
        String object_type = scanner.next();
        System.out.println("Insert build date");
        String build_date = scanner.next();
        java.util.Date newDate = new java.util.Date(build_date);
        java.sql.Date sqlDate = new java.sql.Date(newDate.getTime());
        Object object = new Object(address, object_type, sqlDate);
        session.save(object);
    }

    private void read(Session session) {
        List<Object> objects = session.createQuery("SELECT o from Object o", Object.class).getResultList();
        System.out.println(objects);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert object id");
        int id = scanner.nextInt();

        System.out.println("Insert 1 to change address");
        int choice = scanner.nextInt();
        if(choice == 1)
        {
            System.out.println("Insert object name");
            String address = scanner.next();
            Object object = session.get(Object.class, id);
            object.setAddress(address);
            session.save(object);
        }

        System.out.println("Insert 2 to change object_type");
        choice = scanner.nextInt();
        if(choice == 2) {
            System.out.println("Insert object address");
            String object_type = scanner.next();
            Object object = session.get(Object.class, id);
            object.setObject_type(object_type);
            session.save(object);
        }

        System.out.println("Insert 3 to change build_date");
        choice = scanner.nextInt();
        if(choice == 3) {
            System.out.println("Insert object build date");
            Object object = session.get(Object.class, id);
            String build_date = scanner.next();
            java.util.Date newDate = new java.util.Date(build_date);
            java.sql.Date sqlDate = new java.sql.Date(newDate.getTime());
            object.setBuild_date(sqlDate);
            session.save(object);
        }
    }
    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert object id");
        int id = scanner.nextInt();
        Object object = session.get(Object.class, id);
        session.delete(object);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert object address");
        String address = scanner.next();
        List<Object> objects = session.createQuery("SELECT o from Object o WHERE address = \'" + address + "\'", Object.class).getResultList();
        System.out.println(objects);
    }
}
