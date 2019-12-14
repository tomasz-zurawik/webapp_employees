package hibernate;

import lombok.*;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Employees")
@NoArgsConstructor
@RequiredArgsConstructor
public class Employees<filePathname> implements HibernateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter
    @Setter
    private int id;

    @Column(name = "LastName")
    @NonNull
    @Getter
    @Setter
    private String lastName;

    @Column(name = "FirstName")
    @NonNull
    @Getter
    @Setter
    private String firstName;

    @Column(name = "Address")
    @NonNull
    @Getter
    @Setter
    private String address;

    @Column(name = "City")
    @NonNull
    @Getter
    @Setter
    private String city;

    @Column(name = "Salary")
    @NonNull
    @Getter
    @Setter
    private int salary;

    @Column(name = "Age")
    @NonNull
    @Getter
    @Setter
    private int age;

    @Column(name = "StartJobDate")
    @Transient
    private Date startJobDate;

    @Column(name = "Benefit")
    @NonNull
    @Getter
    @Setter
    private int benefit;

    @Column(name = "Email")
    @NonNull
    @Getter
    @Setter
    private String email;

    @Column(name = "Image")
    @Getter
    @Setter
    private Blob imageAsBlob;

    @Transient
    @Getter
    @Setter
    private String imageAsBase64;

    @Transient
    @Getter
    @Setter
    private String pathname;

    @Transient
    @Getter
    @Setter
    private String fileFormat;

    @OneToMany(mappedBy = "employees", orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    @Getter
    @Setter
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

    public void splitPathname() {
        if (pathname != null) {
            char[] stringCharArray = pathname.toCharArray();
            for (int i = 0; i < stringCharArray.length; i++) {
                if (stringCharArray[i] == '.') {
                    StringBuffer sb = new StringBuffer();
                    for (int j = i + 1; j < stringCharArray.length; j++) {
                        sb.append(stringCharArray[j]);
                    }
                    setFileFormat(sb.toString());
                }
            }
        } else {
            System.out.println("pathname == null");
        }
    }


    public void createImageAsBlob() {
        if (pathname != null) {
            splitPathname();
            byte[] imageAsBytes = getFile(this.pathname, this.fileFormat);
            try {
                setImageAsBlob(new SerialBlob(imageAsBytes));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getFile(String pathname, String fileFormat) {
        File file = new File(pathname);
        if (file.exists()) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, fileFormat, byteOutStream);
                return byteOutStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void createImageAsBase64() {
        if (getImageAsBlob() != null) {
            byte[] imageByte = new byte[0];
            try {
                imageByte = getImageAsBlob().getBytes(1, (int) getImageAsBlob().length());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            imageAsBase64 = Base64Utils.encodeToString(imageByte);
        } else {
            System.out.println("imageAsBlob == null");
        }
    }
}