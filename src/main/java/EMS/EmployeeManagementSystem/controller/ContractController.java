package EMS.EmployeeManagementSystem.controller;

import EMS.EmployeeManagementSystem.models.contract.ContractBindingModel;
import EMS.EmployeeManagementSystem.models.contract.ContractResponseModel;
import EMS.EmployeeManagementSystem.models.message.ResponseMessage;
import EMS.EmployeeManagementSystem.service.impl.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contract")
public class ContractController {

    private final ContractServiceImpl contractService;

    @Autowired
    public ContractController(ContractServiceImpl contractService) {
        this.contractService = contractService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createContract(@RequestBody ContractBindingModel contractBindingModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contractService.createContract(contractBindingModel));
    }

    @PostMapping("/get/{userID}")
    public ResponseEntity<ContractResponseModel> getContractByUserId(@PathVariable Long userID) {
        return ResponseEntity.status(HttpStatus.OK).body(contractService.getContractByUserId(userID));
    }

    @PutMapping("/terminated/{userID}")
    public ResponseEntity<ResponseMessage> terminatedContractByUserId(@PathVariable Long userID) {
        return ResponseEntity.status(HttpStatus.OK).body(contractService.terminateContractByUserId(userID));
    }
}
