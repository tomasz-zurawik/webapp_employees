package hibernate;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "Images")
@Data
@NoArgsConstructor
public class Images{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Name")
    @NonNull
    private String name;

    @Column(name = "Image")
    @NonNull
    private Blob image;

}