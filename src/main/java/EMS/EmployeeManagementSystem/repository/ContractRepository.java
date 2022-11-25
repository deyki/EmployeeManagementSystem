package EMS.EmployeeManagementSystem.repository;

import EMS.EmployeeManagementSystem.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("SELECT c FROM Contract c WHERE c.user.userID = ?1")
    Optional<Contract> findByUserID(Long userID);
}
