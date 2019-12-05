package employees;

import hibernate.Employees;
import hibernate.HibernateDao;
import mail.SendEmail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmpController {
    private List<Employees> list;
    private HibernateDao employeeDao;
    private StringBuilder sb;

    public EmpController() {
        employeeDao = new HibernateDao();
        list = employeeDao.getEmployees();
        sb = new StringBuilder();
    }

    @RequestMapping("/empform")
    public ModelAndView showform(){
        return new ModelAndView("empform","command", new Employees());
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("employees") Employees employees){
        if(employees.getId() < 1) {
            System.out.println("New emp");
            employees.setId(list.size()+1);
            list.add(employees);
            StringBuilder emailContent = sb.append("Witaj ").append(employees.getFirstName()).append(" ").append(employees.getLastName()).append(". Twoje dane zostały poprawnie dodane do bazy danych.");
            SendEmail.send(emailContent, employees.getEmail());
            sb.setLength(0);
            employeeDao.save(employees);
        } else {
            Employees emp1 = getEmployeesById(employees.getId());
            emp1.setLastName(employees.getLastName());
            emp1.setFirstName(employees.getFirstName());
            emp1.setAddress(employees.getAddress());
            emp1.setCity(employees.getCity());
            emp1.setSalary(employees.getSalary());
            emp1.setAge(employees.getAge());
            emp1.setBenefit(employees.getBenefit());
            //emp1.setStartJobDate(employees.getStartJobDate());
            StringBuilder emailContent = sb.append("Witaj ").append(emp1.getFirstName()).append(" ").append(emp1.getLastName()).append(". Twoje dane zostały poprawnie zaktualizowane i dodane do bazy danych.");
            SendEmail.send(emailContent, emp1.getEmail());
            sb.setLength(0);
            employeeDao.update(emp1);
            //  emp1.setDesignation(employees.getDesignation());
            //  emp1.setName(employees.getName());
            //  emp1.setSalary(employees.getSalary());
        }
        System.out.println(employees.getFirstName()+" "+employees.getSalary()+" "+employees.getLastName());
        return new ModelAndView("redirect:/viewemp");
    }

    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public ModelAndView delete(@RequestParam String id){
        Employees emp1 = getEmployeesById(Integer.parseInt(id));
        list.remove(emp1);
        StringBuilder emailContent = sb.append("Witaj ").append(emp1.getFirstName()).append(" ").append(emp1.getLastName()).append(". Twoje dane zostały poprawnie usunięte z bazy danych.");
        SendEmail.send(emailContent, emp1.getEmail());
        sb.setLength(0);
        employeeDao.delete(emp1);
        return new ModelAndView("redirect:/viewemp");
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public ModelAndView edit(@RequestParam String id){
        Employees employees = getEmployeesById(Integer.parseInt(id));
        return new ModelAndView("empform","command", employees);
    }

    @RequestMapping(value="/test", method=RequestMethod.POST)
    public ModelAndView test(){
        System.out.println("Test");
        return new ModelAndView("redirect:/viewemp");
    }

    @RequestMapping("/viewemp")
    public ModelAndView viewemp(){
        return new ModelAndView("viewemp","list", list);
    }

    private Employees getEmployeesById(@RequestParam int id) {
        return list.stream().filter(f -> f.getId() == id).findFirst().get();
    }
}