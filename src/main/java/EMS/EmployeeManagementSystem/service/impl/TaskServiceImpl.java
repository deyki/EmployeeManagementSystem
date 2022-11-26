package EMS.EmployeeManagementSystem.service.impl;

import EMS.EmployeeManagementSystem.entity.Task;
import EMS.EmployeeManagementSystem.entity.User;
import EMS.EmployeeManagementSystem.models.message.ResponseMessage;
import EMS.EmployeeManagementSystem.models.task.TaskBindingModel;
import EMS.EmployeeManagementSystem.models.task.TaskResponseModel;
import EMS.EmployeeManagementSystem.repository.TaskRepository;
import EMS.EmployeeManagementSystem.repository.UserRepository;
import EMS.EmployeeManagementSystem.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskServiceImpl(UserRepository userRepository, TaskRepository taskRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ResponseMessage createTask(TaskBindingModel taskBindingModel) {
        User user = userRepository
                .findByUsername(taskBindingModel.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        Task task = new Task();
        task.setUser(user);
        task.setContent(task.getContent());
        task.setIsDone(false);
        taskRepository.save(task);

        return new ResponseMessage("Task successfully created!");
    }


    @Override
    public TaskResponseModel getTaskById(Long taskID) {
        Task task = taskRepository
                .findById(taskID)
                .orElseThrow(() -> new EntityNotFoundException("Task not found!"));

        TaskResponseModel taskResponseModel = modelMapper.map(task.getUser().getUserProfileDetails(), TaskResponseModel.class);
        taskResponseModel.setUsername(task.getUser().getUsername());
        taskResponseModel.setContent(task.getContent());
        taskResponseModel.setIsDone(task.getIsDone());

        return taskResponseModel;
    }


    @Override
    public List<TaskResponseModel> getTasksByUserId(Long userID) {
        List<Task> tasks = taskRepository
                .findAll()
                .stream()
                .filter(task -> task.getUser().getUserID().equals(userID))
                .collect(Collectors.toList());

        List<TaskResponseModel> taskResponseModels = new ArrayList<>();

        for (Task task : tasks) {
            TaskResponseModel taskResponseModel = new TaskResponseModel();
            modelMapper.map(taskResponseModel, task);
            modelMapper.map(taskResponseModel, task.getUser().getUsername());
            modelMapper.map(taskResponseModel, task.getUser().getUserProfileDetails());
            taskResponseModels.add(taskResponseModel);
        }

        return taskResponseModels;
    }


    @Override
    public ResponseMessage updateTaskIsDoneStatus(Long taskID) {
        Task task = taskRepository
                .findById(taskID)
                .orElseThrow(() -> new EntityNotFoundException("Task not found!"));

        task.setIsDone(true);
        taskRepository.save(task);

        return new ResponseMessage("Task status successfully changed!");
    }
}
