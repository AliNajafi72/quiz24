package ir.maktabsharif.quiz24.commands;

import ir.maktabsharif.quiz24.entities.mysql.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCommand {

    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Size(min = 10, max = 10, message = "کد ملی 10 رقمی را وارد کنید")
    @Positive
    private String idNumber;

    @NotBlank
    @Min(value = 11, message = "شماره تلفن همراه نمیتواند کم تر از 11 رقم باشد")
    @Positive
    private String phoneNumber;

    @NotBlank
    @Min(10)
    private String address;

    private UserStatus status;
}
