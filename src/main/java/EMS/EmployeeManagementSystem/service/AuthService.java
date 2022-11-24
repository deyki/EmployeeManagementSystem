package EMS.EmployeeManagementSystem.service;

import EMS.EmployeeManagementSystem.error.InvalidPasswordException;
import EMS.EmployeeManagementSystem.error.RoleNotFoundException;
import EMS.EmployeeManagementSystem.models.auth.AuthBindingModel;
import EMS.EmployeeManagementSystem.models.auth.AuthResponseModel;
import EMS.EmployeeManagementSystem.models.message.ResponseMessage;
import org.apache.coyote.Response;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    ResponseMessage createAdmin(AuthBindingModel authBindingModel) throws RoleNotFoundException;

    ResponseMessage createEmployee(AuthBindingModel authBindingModel) throws RoleNotFoundException;

    ResponseMessage createModerator(AuthBindingModel authBindingModel) throws RoleNotFoundException;

    AuthResponseModel signIn(AuthBindingModel authBindingModel) throws InvalidPasswordException;
}
