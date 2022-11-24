package EMS.EmployeeManagementSystem.service;

import EMS.EmployeeManagementSystem.error.InvalidPasswordException;
import EMS.EmployeeManagementSystem.models.message.ResponseMessage;
import EMS.EmployeeManagementSystem.models.user.PasswordBindingModel;
import EMS.EmployeeManagementSystem.models.user.UserProfileDetailsBindingModel;
import EMS.EmployeeManagementSystem.models.user.UserResponseModel;
import EMS.EmployeeManagementSystem.models.user.UsernameBindingModel;

public interface UserService {

    ResponseMessage createUserProfileDetails(Long userID, UserProfileDetailsBindingModel userProfileDetailsBindingModel);

    UserResponseModel getUserById(Long userID);

    ResponseMessage updateUserProfileDetailsById(Long userID, UserProfileDetailsBindingModel userProfileDetailsBindingModel);

    ResponseMessage updateUsernameById(Long userID, UsernameBindingModel usernameBindingModel);

    ResponseMessage updatePasswordById(Long userID, PasswordBindingModel passwordBindingModel) throws InvalidPasswordException;
}
