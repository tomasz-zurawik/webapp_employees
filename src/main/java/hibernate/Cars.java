package hibernate;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Cars")
@ToString
@NoArgsConstructor
public class Cars implements HibernateEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter @Setter
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false, referencedColumnName="ID")
    @Getter @Setter
    public Employees employees;

    @Column(name = "Name")
    @Getter @Setter
    private String name;

    @Column(name = "Model")
    @Getter @Setter
    private String model;

    @Column(name = "RegistrationDate")
    @Getter @Setter
    @ToString.Exclude
    private Date registrationDate;
}
