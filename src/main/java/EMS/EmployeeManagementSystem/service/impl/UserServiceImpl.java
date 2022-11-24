package EMS.EmployeeManagementSystem.service.impl;

import EMS.EmployeeManagementSystem.entity.User;
import EMS.EmployeeManagementSystem.entity.UserProfileDetails;
import EMS.EmployeeManagementSystem.error.InvalidPasswordException;
import EMS.EmployeeManagementSystem.models.message.ResponseMessage;
import EMS.EmployeeManagementSystem.models.user.PasswordBindingModel;
import EMS.EmployeeManagementSystem.models.user.UserProfileDetailsBindingModel;
import EMS.EmployeeManagementSystem.models.user.UserResponseModel;
import EMS.EmployeeManagementSystem.models.user.UsernameBindingModel;
import EMS.EmployeeManagementSystem.repository.UserProfileDetailsRepository;
import EMS.EmployeeManagementSystem.repository.UserRepository;
import EMS.EmployeeManagementSystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileDetailsRepository userProfileDetailsRepository;
    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProfileDetailsRepository userProfileDetailsRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userProfileDetailsRepository = userProfileDetailsRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public ResponseMessage createUserProfileDetails(Long userID, UserProfileDetailsBindingModel userProfileDetailsBindingModel) {
        User user = userRepository
                .findById(userID)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        UserProfileDetails userProfileDetails = modelMapper.map(userProfileDetailsBindingModel, UserProfileDetails.class);
        userProfileDetails.setUser(user);
        userProfileDetailsRepository.save(userProfileDetails);

        user.setUserProfileDetails(userProfileDetails);
        userRepository.save(user);

        return new ResponseMessage("User profile details successfully created!");
    }


    @Override
    public UserResponseModel getUserById(Long userID) {
        User user = userRepository
                .findById(userID)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        UserResponseModel userResponseModel = modelMapper.map(user.getUserProfileDetails(), UserResponseModel.class);
        userResponseModel.setUsername(user.getUsername());

        return userResponseModel;
    }

    @Override
    public ResponseMessage updateUserProfileDetailsById(Long userID, UserProfileDetailsBindingModel userProfileDetailsBindingModel) {
        User user = userRepository
                .findById(userID)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        UserProfileDetails userProfileDetails = modelMapper.map(userProfileDetailsBindingModel, UserProfileDetails.class);
        userProfileDetailsRepository.save(userProfileDetails);

        user.setUserProfileDetails(userProfileDetails);
        userRepository.save(user);

        return new ResponseMessage("User profile details successfully updated!");
    }

    @Override
    public ResponseMessage updateUsernameById(Long userID, UsernameBindingModel usernameBindingModel) {
        User user = userRepository
                .findById(userID)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        user.setUsername(usernameBindingModel.getUsername());
        userRepository.save(user);

        return new ResponseMessage("Username updated!");
    }

    @Override
    public ResponseMessage updatePasswordById(Long userID, PasswordBindingModel passwordBindingModel) throws InvalidPasswordException {
        User user = userRepository
                .findById(userID)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        boolean validPassword = bCryptPasswordEncoder.matches(passwordBindingModel.getOldPassword(), user.getPassword());
        if (!validPassword) {
            throw new InvalidPasswordException("Incorrect old password!");
        }

        user.setPassword(bCryptPasswordEncoder.encode(passwordBindingModel.getNewPassword()));
        userRepository.save(user);

        return new ResponseMessage("Password successfully changed!");
    }
}
