package hibernate;

import lombok.*;
import org.springframework.util.Base64Utils;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Employees")
@NoArgsConstructor
@RequiredArgsConstructor
public class Employees implements HibernateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter @Setter
    private int id;

    @Column(name = "LastName")
    @NonNull
    @Getter @Setter
    private String lastName;

    @Column(name = "FirstName")
    @NonNull
    @Getter @Setter
    private String firstName;

    @Column(name = "Address")
    @NonNull
    @Getter @Setter
    private String address;

    @Column(name = "City")
    @NonNull
    @Getter @Setter
    private String city;

    @Column(name = "Salary")
    @NonNull
    @Getter @Setter
    private int salary;

    @Column(name = "Age")
    @NonNull
    @Getter @Setter
    private int age;

    @Column(name = "StartJobDate")
    @Transient
    private Date startJobDate;

    @Column(name = "Benefit")
    @NonNull
    @Getter @Setter
    private int benefit;

    @Column(name = "Email")
    @NonNull
    @Getter @Setter
    private String email;

    @Column(name = "Image")
    @Getter @Setter
    private Blob image;

    @Transient
    @Getter @Setter
    private String imgString;

    @OneToMany(mappedBy = "employees", orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    @Getter @Setter
    private Set<Cars> cars;

    @OneToMany(mappedBy = "employees", orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    @Getter
    private Set<Phones> phones;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "EmployeesWithPrinters",
            joinColumns = @JoinColumn(name = "Employee_ID"),
            inverseJoinColumns = @JoinColumn(name = "Printer_ID")
    )
    @ToString.Exclude
    @Getter
    private Set<Printers> printers = new HashSet<>();

    public void addPrinters(Printers printer) {
        this.printers.add(printer);
        printer.getEmployees().add(this);
    }

    public void removePrinters(Printers printer) {
        this.printers.remove(printer);
        printer.getEmployees().remove(this);
    }

    public void setImgString(){
        if (getImage() != null) {
            byte[] imageByte = new byte[0];
            try {
                imageByte = getImage().getBytes(1, (int) getImage().length());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            imgString = Base64Utils.encodeToString(imageByte);
        }
    }
}