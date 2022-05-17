package logic;

import models.my.Contract;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


public class MainSQLRequestLogic {
    public void work (SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Contract> contracts = session.createQuery("SELECT c FROM Contract c",
                Contract.class).getResultList();
        System.out.println(" Date\t\t\t\t\t\t\tVolume\t\t\t\tPrice");
        for (var contract : contracts) {
            System.out.format("\n%s\t\t\t%s\t\t\t%s", contract.getDate_of_conclusion().toString(),
                    contract.getVolume(), contract.getFinal_price());
        }
        System.out.println("\n");
        session.getTransaction().commit();
    }
}
