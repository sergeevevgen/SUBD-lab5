import logic.*;
import logic.my.*;
import models.my.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.lang.Object;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.close();
//        HibernateUtil.shutdown();

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Company.class)
                .addAnnotatedClass(Company_Service.class)
                .addAnnotatedClass(Service.class)
                .addAnnotatedClass(Service_Material.class)
                .addAnnotatedClass(Material.class)
                .addAnnotatedClass(Zdanie.class)
                .addAnnotatedClass(Contract.class)
                .buildSessionFactory();

        boolean isWork = true;
        while(isWork){
            System.out.println("Insert 1 to work with companies");
            System.out.println("Insert 2 to work with contracts");
            System.out.println("Insert 3 to work with materials");
            System.out.println("Insert 4 to work with objects");
            System.out.println("Insert 5 to work with services");
            System.out.println("Insert 6 to exit");
            System.out.println("Insert 7 to see the main request");

            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();

            switch (i) {
                case 1 -> {
                    CompanyLogic companyLogic = new CompanyLogic();
                    companyLogic.work(sessionFactory);
                }
                case 2 -> {
                    ContractLogic contractLogic = new ContractLogic();
                    contractLogic.work(sessionFactory);
                }
                case 3 -> {
                    MaterialLogic materialLogic = new MaterialLogic();
                    materialLogic.work(sessionFactory);
                }
                case 4 -> {
                    ObjectLogic objectLogic = new ObjectLogic();
                    objectLogic.work(sessionFactory);
                }
                case 5 -> {
                    ServiceLogic serviceLogic = new ServiceLogic();
                    serviceLogic.work(sessionFactory);
                }
                case 6 -> isWork = false;
                case 7 -> {
                    MainSQLRequestLogic mainSQLRequestLogic = new MainSQLRequestLogic();
                    mainSQLRequestLogic.work(sessionFactory);
                }
            }
        }
        sessionFactory.close();
    }
}