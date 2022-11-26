package EMS.EmployeeManagementSystem.models.task;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskResponseModel {

    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String content;
    private Boolean isDone;
}
