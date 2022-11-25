package EMS.EmployeeManagementSystem.models.contract;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContractBindingModel {

    private String jobPosition;
    private Integer netSalary;
    private String username;
}
