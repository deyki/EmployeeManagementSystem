package EMS.EmployeeManagementSystem.service;

import EMS.EmployeeManagementSystem.entity.Role;
import EMS.EmployeeManagementSystem.error.RoleNotFoundException;

import java.util.Set;

public interface RoleService {

    void seedRolesInDb();

    Set<Role> findAllRoles();

    Role findByAuthority(String authority) throws RoleNotFoundException;
}
