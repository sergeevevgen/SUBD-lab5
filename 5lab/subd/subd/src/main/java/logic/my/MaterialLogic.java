package logic.my;

import models.my.Company;
import models.my.Material;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class MaterialLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Insert 1 to create material");
        System.out.println("Insert 2 to read material");
        System.out.println("Insert 3 to  update material");
        System.out.println("Insert 4 to delete material");
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
        System.out.println("Insert material name");
        String name = scanner.nextLine();
        System.out.println("Insert material price");
        float price = scanner.nextFloat();
        System.out.println("Insert material unit_measurement");
        String unit_measurement = scanner.nextLine();
        Material material = new Material(name, price, unit_measurement);
        session.save(material);
    }

    private void read(Session session) {
        List<Material> materials = session.createQuery("SELECT m from Material m", Material.class).getResultList();
        System.out.println(materials);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert material id");
        var id = scanner.nextLong();

        System.out.println("Insert 1 to change name");
        int choice = scanner.nextInt();
        if(choice == 1)
        {
            System.out.println("Insert material name");
            String name = scanner.nextLine();
            Material material = session.get(Material.class, id);
            material.setName(name);
            session.save(material);
        }

        System.out.println("Insert 2 to change price");
        choice = scanner.nextInt();
        if(choice == 2) {
            System.out.println("Insert material price");
            float price = scanner.nextFloat();
            Material material = session.get(Material.class, id);
            material.setPrice(price);
            session.save(material);
        }

        System.out.println("Insert 3 to change unit measurement");
        choice = scanner.nextInt();
        if(choice == 3) {
            System.out.println("Insert material unit measurement");
            String unit_measurement = scanner.nextLine();
            Material material = session.get(Material.class, id);
            material.setUnit_measurement(unit_measurement);
            session.save(material);
        }
    }
    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert material id");
        var id = scanner.nextLong();
        Material material = session.get(Material.class, id);
        session.delete(material);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert material name");
        String name = scanner.nextLine();
        List<Material> materials = session.createQuery("SELECT m from Material m WHERE name = '" + name + "'", Material.class).getResultList();
        System.out.println(materials);
    }
}
