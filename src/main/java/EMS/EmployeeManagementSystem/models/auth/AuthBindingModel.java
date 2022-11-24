package EMS.EmployeeManagementSystem.models.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthBindingModel {

    private String username;
    private String password;
}
