package logic.my;

import models.my.Contract;
import models.my.Zdanie;
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
        String address = scanner.nextLine();
        System.out.println("Insert object object_type");
        String object_type = scanner.nextLine();
        System.out.println("Insert build date");
        String build_date = scanner.nextLine();
        java.util.Date newDate = new java.util.Date(build_date);
        java.sql.Date sqlDate = new java.sql.Date(newDate.getTime());
        Zdanie zdanie = new Zdanie(address, object_type, sqlDate);
        session.save(zdanie);
    }

    private void read(Session session) {
        List<Zdanie> zdanies = session.createQuery("SELECT o from Zdanie o", Zdanie.class).getResultList();
        System.out.println(zdanies);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert object id");
        var id = scanner.nextLong();

        System.out.println("Insert 1 to change address");
        int choice = scanner.nextInt();
        if(choice == 1)
        {
            System.out.println("Insert object name");
            String address = scanner.nextLine();
            Zdanie zdanie = session.get(Zdanie.class, id);
            zdanie.setAddress(address);
            session.save(zdanie);
        }

        System.out.println("Insert 2 to change object_type");
        choice = scanner.nextInt();
        if(choice == 2) {
            System.out.println("Insert object address");
            String object_type = scanner.nextLine();
            Zdanie zdanie = session.get(Zdanie.class, id);
            zdanie.setObject_type(object_type);
            session.save(zdanie);
        }

        System.out.println("Insert 3 to change build_date");
        choice = scanner.nextInt();
        if(choice == 3) {
            System.out.println("Insert object build date");
            Zdanie zdanie = session.get(Zdanie.class, id);
            String build_date = scanner.next();
            java.util.Date newDate = new java.util.Date(build_date);
            java.sql.Date sqlDate = new java.sql.Date(newDate.getTime());
            zdanie.setBuild_date(sqlDate);
            session.save(zdanie);
        }
    }
    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert object id");
        var id = scanner.nextLong();
        Zdanie zdanie = session.get(Zdanie.class, id);
        var contracts = session.createQuery("SELECT c FROM Contract c WHERE zdanie = \'" + id + "\'", Contract.class).getResultList();
        for (var contract : contracts) {
            contract.setZdanie(null);
            session.saveOrUpdate(contract);
        }
        session.delete(zdanie);
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert object address");
        String address = scanner.nextLine();
        List<Zdanie> zdanies = session.createQuery("SELECT o from Zdanie o WHERE address = \'" + address + "\'", Zdanie.class).getResultList();
        System.out.println(zdanies);
    }
}
