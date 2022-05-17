package logic.my;

import models.my.Company;
import models.my.Service;
import models.my.Service_Material;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class ServiceLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Insert 1 to create service");
        System.out.println("Insert 2 to read service");
        System.out.println("Insert 3 to update service");
        System.out.println("Insert 4 to delete service");
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
        System.out.println("Insert service name");
        String name = scanner.nextLine();
        Service service = new Service(name);
        session.save(service);
    }

    private void read(Session session) {
        List<Service> services = session.createQuery("SELECT s from Service s", Service.class).getResultList();
        System.out.println(services);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert service id");
        var id = scanner.nextLong();

        System.out.println("Insert 1 to change name");
        int choice = scanner.nextInt();
        if(choice == 1)
        {
            System.out.println("Insert service name");
            String name = scanner.nextLine();
            Service service = session.get(Service.class, id);
            service.setName(name);
            session.save(service);
        }

        System.out.println("Insert 2 to add material");
        choice = scanner.nextInt();
        if(choice == 2)
        {
            System.out.println("Insert material id");
            var material_id = scanner.nextLong();
            System.out.println("Insert count");
            var count = scanner.nextFloat();
            Service_Material service_material = new Service_Material(id, material_id, count);
            session.save(service_material);
        }
    }
    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert service id");
        var id = scanner.nextLong();
        Service service = session.get(Service.class, id);
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(service);
            transaction.commit();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert service name");
        String name = scanner.nextLine();
        List<Service> services = session.createQuery("SELECT s from Service s WHERE name = '" + name + "'", Service.class).getResultList();
        System.out.println(services);
    }
}
