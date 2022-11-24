package EMS.EmployeeManagementSystem.service;

import EMS.EmployeeManagementSystem.error.InvalidPasswordException;
import EMS.EmployeeManagementSystem.error.RoleNotFoundException;
import EMS.EmployeeManagementSystem.models.auth.AuthBindingModel;
import EMS.EmployeeManagementSystem.models.auth.AuthResponseModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    String createAdmin(AuthBindingModel authBindingModel) throws RoleNotFoundException;

    String createEmployee(AuthBindingModel authBindingModel) throws RoleNotFoundException;

    String createModerator(AuthBindingModel authBindingModel) throws RoleNotFoundException;

    AuthResponseModel signIn(AuthBindingModel authBindingModel) throws InvalidPasswordException;
}
