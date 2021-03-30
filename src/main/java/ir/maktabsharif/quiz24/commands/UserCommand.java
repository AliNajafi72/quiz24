package ir.maktabsharif.quiz24.commands;

import ir.maktabsharif.quiz24.entities.mysql.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCommand {
    private Long id;
    private String username;
    private String password;
    private String idNumber;
    private String phoneNumber;
    private String address;
    private UserStatus status;
}
