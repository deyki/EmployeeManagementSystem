package EMS.EmployeeManagementSystem.controller;

import EMS.EmployeeManagementSystem.error.InvalidPasswordException;
import EMS.EmployeeManagementSystem.error.RoleNotFoundException;
import EMS.EmployeeManagementSystem.models.auth.AuthBindingModel;
import EMS.EmployeeManagementSystem.models.auth.AuthResponseModel;
import EMS.EmployeeManagementSystem.models.message.ResponseMessage;
import EMS.EmployeeManagementSystem.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServiceImpl authService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthServiceImpl authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/createAdmin")
    public ResponseEntity<ResponseMessage> createAdmin(@RequestBody AuthBindingModel authBindingModel) throws RoleNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.createAdmin(authBindingModel));
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<ResponseMessage> createEmployee(@RequestBody AuthBindingModel authBindingModel) throws RoleNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.createEmployee(authBindingModel));
    }

    @PostMapping("/createModerator")
    public ResponseEntity<ResponseMessage> createModerator(@RequestBody AuthBindingModel authBindingModel) throws RoleNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.createModerator(authBindingModel));
    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthResponseModel> signIn(@RequestBody AuthBindingModel authBindingModel) throws InvalidPasswordException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authBindingModel.getUsername(), authBindingModel.getPassword()));
        return ResponseEntity.status(HttpStatus.OK).body(authService.signIn(authBindingModel));
    }
}
