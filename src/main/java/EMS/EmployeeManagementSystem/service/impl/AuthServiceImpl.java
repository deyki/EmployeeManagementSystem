package EMS.EmployeeManagementSystem.service.impl;

import EMS.EmployeeManagementSystem.entity.User;
import EMS.EmployeeManagementSystem.error.InvalidPasswordException;
import EMS.EmployeeManagementSystem.error.RoleNotFoundException;
import EMS.EmployeeManagementSystem.models.auth.AuthBindingModel;
import EMS.EmployeeManagementSystem.models.auth.AuthResponseModel;
import EMS.EmployeeManagementSystem.models.message.ResponseMessage;
import EMS.EmployeeManagementSystem.repository.UserRepository;
import EMS.EmployeeManagementSystem.security.JWTUtil;
import EMS.EmployeeManagementSystem.service.AuthService;
import EMS.EmployeeManagementSystem.service.RoleService;
import com.google.common.collect.Sets;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final JWTUtil jwtUtil;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper, RoleService roleService, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User: %s not found!", username)));
    }


    @Override
    public ResponseMessage createAdmin(AuthBindingModel authBindingModel) throws RoleNotFoundException {
        userRepository.findByUsername(authBindingModel.getUsername()).ifPresent(user -> {
            throw new EntityExistsException(String.format("User: %s already exist!", authBindingModel.getUsername()));
        });

        if (userRepository.count() == 0) {
            roleService.seedRolesInDb();
            User user = modelMapper.map(authBindingModel, User.class);
            user.setPassword(bCryptPasswordEncoder.encode(authBindingModel.getPassword()));
            user.setAuthorities(Sets.newHashSet(roleService.findByAuthority("ADMIN")));
            userRepository.save(user);
        } else {
            throw new EntityExistsException("Admin account already exist!");
        }

        return new ResponseMessage("Admin account created!");
    }


    @Override
    public ResponseMessage createEmployee(AuthBindingModel authBindingModel) throws RoleNotFoundException {
        userRepository.findByUsername(authBindingModel.getUsername()).ifPresent(user -> {
            throw new EntityExistsException(String.format("User: %s already exist!", authBindingModel.getUsername()));
        });

        User user = modelMapper.map(authBindingModel, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(authBindingModel.getPassword()));
        user.setAuthorities(Sets.newHashSet(roleService.findByAuthority("EMPLOYEE")));
        userRepository.save(user);

        return new ResponseMessage("Employee account created!");
    }


    @Override
    public ResponseMessage createModerator(AuthBindingModel authBindingModel) throws RoleNotFoundException {
        userRepository.findByUsername(authBindingModel.getUsername()).ifPresent(user -> {
            throw new EntityExistsException(String.format("User: %s already exist!", authBindingModel.getUsername()));
        });

        User user = modelMapper.map(authBindingModel, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(authBindingModel.getPassword()));
        user.setAuthorities(Sets.newHashSet(roleService.findByAuthority("MODERATOR")));
        userRepository.save(user);

        return new ResponseMessage("Moderator account created");
    }


    @Override
    public AuthResponseModel signIn(AuthBindingModel authBindingModel) throws InvalidPasswordException {
        User user = userRepository.findByUsername(authBindingModel.getUsername())
                .orElseThrow(() -> new EntityNotFoundException(String.format("User: %s not found!", authBindingModel.getUsername())));

        boolean validPassword = bCryptPasswordEncoder.matches(authBindingModel.getPassword(), user.getPassword());
        if (!validPassword) {
            throw new InvalidPasswordException("Invalid password!");
        }

        final String JWToken = jwtUtil.generateToken(user.getUsername());
        return new AuthResponseModel(JWToken);
    }
}
