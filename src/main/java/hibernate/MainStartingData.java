package hibernate;

import java.sql.SQLException;

public class MainStartingData {
    public static void main(String[] args) {

        HibernateDao hibernateDao = new HibernateDao();

        Employees emp1 = new Employees("Test1", "Test1", "Test1", "Test1", 1000, 18, 1, "bik3r94@gmail.com");
        Employees emp2 = new Employees("Test2", "Test2", "Test2", "Test2", 2000, 18, 1, "bik3r94@gmail.com");
        Employees emp3 = new Employees("Test3", "Test3", "Test3", "Test3", 3000, 18, 1, "bik3r94@gmail.com");
        hibernateDao.save(emp1);
        hibernateDao.save(emp2);
        hibernateDao.save(emp3);

        Printers printer1 = new Printers("HP","one");
        Printers printer2 = new Printers("HP","two");
        Printers printer3 = new Printers("HP","three");
        hibernateDao.save(printer1);
        hibernateDao.save(printer2);
        hibernateDao.save(printer3);

        //Dodawanie realcji ManyToMany do bazy - działa
        Employees emp4 = new Employees("Test4", "Test4", "Test4", "Test4", 4000, 18, 1, "bik3r94@gmail.com");
        emp4.addPrinters(printer1);
        hibernateDao.save(emp4);
        Employees emp5 = new Employees("Test5", "Test5", "Test5", "Test5", 4000, 18, 1, "bik3r94@gmail.com");
        emp5.addPrinters(printer1);
        hibernateDao.save(emp5);
        emp3.addPrinters(printer2);
        emp3.addPrinters(printer3);
        hibernateDao.update(emp3);
    }
}
