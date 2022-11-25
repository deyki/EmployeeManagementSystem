package EMS.EmployeeManagementSystem.service;

import EMS.EmployeeManagementSystem.models.contract.ContractBindingModel;
import EMS.EmployeeManagementSystem.models.contract.ContractResponseModel;
import EMS.EmployeeManagementSystem.models.message.ResponseMessage;

import java.util.List;

public interface ContractService {

    ResponseMessage createContract(ContractBindingModel contractBindingModel);

    ContractResponseModel getContractByUserId(Long userID);

    ResponseMessage terminateContractByUserId(Long userID);
}
