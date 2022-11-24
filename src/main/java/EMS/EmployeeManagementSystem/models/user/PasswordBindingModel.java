package EMS.EmployeeManagementSystem.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordBindingModel {

    private String oldPassword;
    private String newPassword;
}
