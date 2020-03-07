package com.example.market.App.dtos.PersonDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ChangeAccountPasswordDto {

    @NotNull(message = "Password can not be empty.")
    private String oldPassword;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$", message = "Invalid password")
    @NotNull(message = "Password can not be empty.")
    private String newPassword;

    @NotNull(message = "Password can not be empty.")
    private String confirmNewPassword;

    public ChangeAccountPasswordDto() {
    }

    public ChangeAccountPasswordDto(String oldPassword, String newPassword, String confirmNewPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
