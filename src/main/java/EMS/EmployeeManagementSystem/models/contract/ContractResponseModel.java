package EMS.EmployeeManagementSystem.models.contract;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ContractResponseModel {

    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Integer phoneNumber;
    private String jobPosition;
    private Date startDate;
    private Date dateOfTermination;
    private Integer netSalary;
}
