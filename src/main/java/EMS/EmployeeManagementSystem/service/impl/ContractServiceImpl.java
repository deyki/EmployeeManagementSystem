package EMS.EmployeeManagementSystem.service.impl;

import EMS.EmployeeManagementSystem.entity.Contract;
import EMS.EmployeeManagementSystem.entity.User;
import EMS.EmployeeManagementSystem.models.contract.ContractBindingModel;
import EMS.EmployeeManagementSystem.models.contract.ContractResponseModel;
import EMS.EmployeeManagementSystem.models.message.ResponseMessage;
import EMS.EmployeeManagementSystem.repository.ContractRepository;
import EMS.EmployeeManagementSystem.repository.UserRepository;
import EMS.EmployeeManagementSystem.service.ContractService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class ContractServiceImpl implements ContractService {

    private final UserRepository userRepository;
    private final ContractRepository contractRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ContractServiceImpl(UserRepository userRepository, ContractRepository contractRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.contractRepository = contractRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseMessage createContract(ContractBindingModel contractBindingModel) {
        User user = userRepository
                .findByUsername(contractBindingModel.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        Contract contract = modelMapper.map(contractBindingModel, Contract.class);
        contract.setStartDate(new Date());
        contract.setUser(user);
        contract.setTerminated(false);
        contractRepository.save(contract);

        user.setContract(contract);
        userRepository.save(user);

        return new ResponseMessage("Contract created!");
    }

    @Override
    public ContractResponseModel getContractByUserId(Long userID) {
        User user = userRepository
                .findById(userID)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        contractRepository.findByUserID(user.getUserID()).orElseThrow(() -> new EntityNotFoundException("This user has no contract!"));

        ContractResponseModel contractResponseModel = modelMapper.map(user.getUserProfileDetails(), ContractResponseModel.class);
        contractResponseModel.setUsername(user.getUsername());
        contractResponseModel.setJobPosition(user.getContract().getJobPosition());
        contractResponseModel.setNetSalary(user.getContract().getNetSalary());
        contractResponseModel.setStartDate(user.getContract().getStartDate());
        contractResponseModel.setDateOfTermination(user.getContract().getDateOfTermination());

        return contractResponseModel;
    }

    @Override
    public ResponseMessage terminateContractByUserId(Long userID) {
        User user = userRepository
                .findById(userID)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        Contract contract = user.getContract();
        contract.setTerminated(true);
        contract.setDateOfTermination(new Date());
        contractRepository.save(contract);

        userRepository.save(user);

        return new ResponseMessage("Contract terminated!");
    }
}
