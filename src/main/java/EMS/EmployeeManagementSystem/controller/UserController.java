package EMS.EmployeeManagementSystem.controller;

import EMS.EmployeeManagementSystem.error.InvalidPasswordException;
import EMS.EmployeeManagementSystem.models.message.ResponseMessage;
import EMS.EmployeeManagementSystem.models.user.PasswordBindingModel;
import EMS.EmployeeManagementSystem.models.user.UserProfileDetailsBindingModel;
import EMS.EmployeeManagementSystem.models.user.UserResponseModel;
import EMS.EmployeeManagementSystem.models.user.UsernameBindingModel;
import EMS.EmployeeManagementSystem.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/createProfileDetails/{userID}")
    public ResponseEntity<ResponseMessage> createUserProfileDetails(@PathVariable Long userID, @RequestBody UserProfileDetailsBindingModel userProfileDetailsBindingModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUserProfileDetails(userID, userProfileDetailsBindingModel));
    }

    @GetMapping("/getUser/{userID}")
    public ResponseEntity<UserResponseModel> getUserById(@PathVariable Long userID) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userID));
    }

    @PutMapping("/updateDetails/{userID}")
    public ResponseEntity<ResponseMessage> updateUserProfileDetailsById(@PathVariable Long userID, @RequestBody UserProfileDetailsBindingModel userProfileDetailsBindingModel) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserProfileDetailsById(userID, userProfileDetailsBindingModel));
    }

    @PutMapping("/updateUsername/{userID}")
    public ResponseEntity<ResponseMessage> updateUsername(@PathVariable Long userID, @RequestBody UsernameBindingModel usernameBindingModel) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUsernameById(userID, usernameBindingModel));
    }

    @PutMapping("/updatePassword/{userID}")
    public ResponseEntity<ResponseMessage> updatePassword(@PathVariable Long userID, @RequestBody PasswordBindingModel passwordBindingModel) throws InvalidPasswordException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updatePasswordById(userID, passwordBindingModel));
    }
}
