package EMS.EmployeeManagementSystem.models.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProfileDetailsBindingModel {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Integer phoneNumber;
}
