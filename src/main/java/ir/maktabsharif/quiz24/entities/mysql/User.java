package ir.maktabsharif.quiz24.entities.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false, unique = true)
    private Long id;
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "ID_NUMBER", unique = true)
    private String idNumber;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private ir.maktabsharif.quiz24.entities.mysql.UserStatus status;

    public boolean isApproved() {
        return this.status.equals(UserStatus.APPROVED);
    }
}
