package logic.my;

import models.my.Company;
import models.my.Contract;
import models.my.Service;
import models.my.Zdanie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class ContractLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Insert 1 to create contract");
        System.out.println("Insert 2 to read contract");
        System.out.println("Insert 3 to update contract");
        System.out.println("Insert 4 to delete contract");
        System.out.println("Insert 5 to filter");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        Session session = null;
        session = sessionFactory.getCurrentSession();
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

        System.out.println("Insert object id");
        var objectId = scanner.nextLong();

        System.out.println("Insert service id");
        var serviceId = scanner.nextLong();

        System.out.println("Insert company id");
        var companyId = scanner.nextLong();

        System.out.println("Insert date of conclusion");
        String date_of_conclusion = scanner.next();
        java.util.Date myDate = new java.util.Date(date_of_conclusion);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        System.out.println("Insert date of end");
        String date_of_end = scanner.next();
        java.util.Date myDate1 = new java.util.Date(date_of_end);
        java.sql.Date sqlDate1 = new java.sql.Date(myDate1.getTime());

        System.out.println("Insert volume");
        float volume = scanner.nextFloat();

        System.out.println("Insert final price");
        float final_price = scanner.nextFloat();

        Contract contract = new Contract(session.get(Zdanie.class, objectId), session.get(Service.class, serviceId), session.get(Company.class, companyId),
                sqlDate, sqlDate1, volume, final_price);

        session.save(contract);
    }

    private void read(Session session) {
        List<Contract> contracts = session.createQuery("SELECT c from Contract c", Contract.class).getResultList();
        System.out.println(contracts);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert contract id");
        var id = scanner.nextLong();

        System.out.println("Insert object id");
        var objectId = scanner.nextLong();

        System.out.println("Insert service id");
        var serviceId = scanner.nextLong();

        System.out.println("Insert company id");
        var companyId = scanner.nextLong();

        System.out.println("Insert date of conclusion");
        String date_of_conclusion = scanner.next();
        java.util.Date myDate = new java.util.Date(date_of_conclusion);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

        System.out.println("Insert date of end");
        String date_of_end = scanner.next();
        java.util.Date myDate1 = new java.util.Date(date_of_end);
        java.sql.Date sqlDate1 = new java.sql.Date(myDate1.getTime());

        System.out.println("Insert volume");
        float volume = scanner.nextFloat();

        System.out.println("Insert final price");
        float final_price = scanner.nextFloat();

        Contract contract = session.get(Contract.class, id);
        contract.setZdanie(session.get(Zdanie.class, objectId));
        contract.setCompany(session.get(Company.class, companyId));
        contract.setService(session.get(Service.class, serviceId));
        contract.setDate_of_conclusion(sqlDate);
        contract.setDate_of_end(sqlDate1);
        contract.setVolume(volume);
        contract.setFinal_price(final_price);
        session.save(contract);
    }

    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert contract id");
        var id = scanner.nextLong();
        Contract contract = session.get(Contract.class, id);
        session.delete(contract);
    }

    private void filterRead(Session session) {
        System.out.println("Insert 1 to filter by object");
        System.out.println("Insert 2 to filter by company");
        System.out.println("Insert 3 to filter by service");
        System.out.println("Insert 4 to filter by date of conclusion");
        System.out.println("Insert 5 to filter by date of end");
        System.out.println("Insert 6 to filter by volume");
        System.out.println("Insert 7 to filter by final price");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Contract> contracts = null;
        switch (i) {
            case 1 -> {
                System.out.println("Insert object id");
                var objectId = scanner.nextLong();
                contracts = session.createQuery("SELECT c from Contract c where zdanie = \'"
                        + objectId + "\'", Contract.class).getResultList();
            }
            case 2 -> {
                System.out.println("Insert company id");
                var companyId = scanner.nextLong();
                contracts = session.createQuery("SELECT c from Contract c where company = \'"
                        + companyId + "\'", Contract.class).getResultList();
            }
            case 3 -> {
                System.out.println("Insert service id");
                var serviceId = scanner.nextLong();
                contracts = session.createQuery("SELECT c from Contract c where service = \'"
                        + serviceId + "\'", Contract.class).getResultList();
            }
            case 4 -> {
                System.out.println("Insert date of conclusion");
                String date = scanner.next();
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                contracts = session.createQuery("SELECT c from Contract c where date_of_conclusion = \'"
                        + sqlDate + "\'", Contract.class).getResultList();
            }
            case 5 -> {
                System.out.println("Insert date of end");
                String date = scanner.next();
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                contracts = session.createQuery("SELECT c from Contract c where date_of_end = \'"
                        + sqlDate + "\'", Contract.class).getResultList();
            }
            case 6 -> {
                System.out.println("Insert volume");
                float volume = scanner.nextFloat();
                contracts = session.createQuery("SELECT c from Contract c where volume = \'"
                        + volume + "\'", Contract.class).getResultList();
            }
            case 7 -> {
                System.out.println("Insert final price");
                float final_price = scanner.nextFloat();
                contracts = session.createQuery("SELECT c from Contract c where final_price = \'"
                        + final_price + "\'", Contract.class).getResultList();
            }
        }
        System.out.println(contracts);
    }
}
