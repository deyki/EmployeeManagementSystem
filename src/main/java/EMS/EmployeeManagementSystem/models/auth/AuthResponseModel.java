package EMS.EmployeeManagementSystem.models.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AuthResponseModel {

    private String JWToken;
}
