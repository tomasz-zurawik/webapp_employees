package hibernate;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Printers")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Printers implements HibernateEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private int id;

        @Column(name = "Name")
        @NonNull
        private String name;

        @Column(name = "Model")
        @NonNull
        private String model;

        @ManyToMany(mappedBy = "printers", fetch = FetchType.EAGER)
        @ToString.Exclude
        private Set<Employees> employees = new HashSet<>();

}
