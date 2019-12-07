package hibernate;

import java.util.Date;
import java.util.List;

public class MainHibernate {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDao();
        Employees employee = new Employees("Test", "Test", "Test", "Test", 1000, 18 , 1, "janek@gmail.com");
        employeeDao.saveEmployee(employee);
        List<Employees> employeesList = employeeDao.getEmployees();

        Employees employeeToUpdate = employeesList.get(0);
        employeeToUpdate.setSalary(9999);

        employeeDao.updateEmployees(employeeToUpdate);
        employeesList.forEach(System.out::println);

       System.out.println(employeesList.get(0).getCars());

        PhoneDao phoneDao = new PhoneDao();

        Phones phones = new Phones();
        phones.setName("Sony");
        phones.setModel("Xperia 10");

        phoneDao.savePhones(phones);
        phoneDao.getPhones().forEach(System.out::println);
    }
}
