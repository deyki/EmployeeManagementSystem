package EMS.EmployeeManagementSystem.controller;

import EMS.EmployeeManagementSystem.models.message.ResponseMessage;
import EMS.EmployeeManagementSystem.models.task.TaskBindingModel;
import EMS.EmployeeManagementSystem.models.task.TaskResponseModel;
import EMS.EmployeeManagementSystem.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskServiceImpl taskService;

    @Autowired
    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createTask(@RequestBody TaskBindingModel taskBindingModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(taskBindingModel));
    }

    @GetMapping("/get/{taskID}")
    public ResponseEntity<TaskResponseModel> getTaskById(@PathVariable Long taskID) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.getTaskById(taskID));
    }

    @GetMapping("/getTasks/{userID}")
    public ResponseEntity<List<TaskResponseModel>> getTasksByUserId(@PathVariable Long userID) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTasksByUserId(userID));
    }

    @PutMapping("/update/{taskID}")
    public ResponseEntity<ResponseMessage> updateTask(@PathVariable Long taskID) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTaskIsDoneStatus(taskID));
    }
}
