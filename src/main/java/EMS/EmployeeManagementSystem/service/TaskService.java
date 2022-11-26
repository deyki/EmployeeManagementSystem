package EMS.EmployeeManagementSystem.service;

import EMS.EmployeeManagementSystem.models.message.ResponseMessage;
import EMS.EmployeeManagementSystem.models.task.TaskBindingModel;
import EMS.EmployeeManagementSystem.models.task.TaskResponseModel;

import java.util.List;

public interface TaskService {

    ResponseMessage createTask(TaskBindingModel taskBindingModel);

    TaskResponseModel getTaskById(Long taskID);

    List<TaskResponseModel> getTasksByUserId(Long userID);

    ResponseMessage updateTaskIsDoneStatus(Long taskID);
}
