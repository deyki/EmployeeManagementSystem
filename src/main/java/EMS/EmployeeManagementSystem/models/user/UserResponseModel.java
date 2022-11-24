package EMS.EmployeeManagementSystem.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseModel {

    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Integer phoneNumber;
}
