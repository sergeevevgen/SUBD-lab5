package logic.my;

import models.my.Company;
import models.my.Company_Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class CompanyLogic {

    public void work(SessionFactory sessionFactory) {
        System.out.println("Insert 1 to create company");
        System.out.println("Insert 2 to read company");
        System.out.println("Insert 3 to update company");
        System.out.println("Insert 4 to delete company");
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
        System.out.println("Insert company name");
        String name = scanner.next();
        System.out.println("Insert company address");
        String address = scanner.next();
        System.out.println("Insert company ceo");
        String ceo = scanner.next();
        System.out.println("Insert date of creation");
        String date_of_creation = scanner.next();
        java.util.Date newDate = new java.util.Date(date_of_creation);
        java.sql.Date sqlDate = new java.sql.Date(newDate.getTime());
        Company company = new Company(name, address, ceo, sqlDate);
        session.save(company);
    }

    private void read(Session session) {
        List<Company> companies = session.createQuery("SELECT c from Company c", Company.class).getResultList();
        System.out.println(companies);
    }

    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert company id");
        var id = scanner.nextLong();

        System.out.println("Insert 1 to change name");
        int choice = scanner.nextInt();
        if(choice == 1)
        {
            System.out.println("Insert company name");
            String name = scanner.next();
            Company company = session.get(Company.class, id);
            company.setName(name);
            session.save(company);
        }

        System.out.println("Insert 2 to change address");
        choice = scanner.nextInt();
        if(choice == 2) {
            System.out.println("Insert company address");
            String address = scanner.next();
            Company company = session.get(Company.class, id);
            company.setAddress(address);
            session.save(company);
        }

        System.out.println("Insert 3 to change ceo");
        choice = scanner.nextInt();
        if(choice == 3) {
            System.out.println("Insert company ceo");
            String ceo = scanner.next();
            Company company = session.get(Company.class, id);
            company.setCeo(ceo);
            session.save(company);
        }

        System.out.println("Insert 4 to change date_of_creation");
        if(choice == 4) {
            System.out.println("Insert date of creation");
            String date_of_creation = scanner.next();
            java.util.Date newDate = new java.util.Date(date_of_creation);
            java.sql.Date sqlDate = new java.sql.Date(newDate.getTime());
            Company company = session.get(Company.class, id);
            company.setDate_of_creation(sqlDate);
            session.save(company);
        }

        System.out.println("Insert 5 to add service");
        choice = scanner.nextInt();
        if(choice == 5) {
            System.out.println("Insert service_id");
            var serviceid = scanner.nextLong();
            System.out.println("Insert price for unit_measurement");
            var unitm = scanner.nextFloat();
            var company_service = new Company_Service(id, serviceid, unitm);
            session.save(company_service);
        }
    }
    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert company id");
        var id = scanner.nextLong();
        Company company = session.get(Company.class, id);
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(company);
            transaction.commit();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert company name");
        String name = scanner.nextLine();
        List<Company> companies = session.createQuery("SELECT c from Company c WHERE name = '" + name + "'", Company.class).getResultList();
        System.out.println(companies);
    }
}

/*SELECT
C.Name, CON.Contract_ID, CON.Date_Of_End, CON.Final_Price
FROM COMPANY C, CONTRACT CON
WHERE C.Company_ID = CON.Company_ID AND CON.Date_Of_Conclusion > '2022-10-10' ORDER BY CON.Final_Price;
*/