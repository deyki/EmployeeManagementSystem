package EMS.EmployeeManagementSystem.service.impl;

import EMS.EmployeeManagementSystem.entity.Role;
import EMS.EmployeeManagementSystem.error.RoleNotFoundException;
import EMS.EmployeeManagementSystem.repository.RoleRepository;
import EMS.EmployeeManagementSystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedRolesInDb() {

        Role admin = new Role();
        admin.setAuthority("ADMIN");

        Role employee = new Role();
        employee.setAuthority("EMPLOYEE");

        Role moderator = new Role();
        moderator.setAuthority("MODERATOR");

        roleRepository.saveAll(new HashSet<>(List.of(admin, employee, moderator)));
    }

    @Override
    public Set<Role> findAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public Role findByAuthority(String authority) throws RoleNotFoundException {
        return roleRepository
                .findByAuthority(authority)
                .orElseThrow(() -> new RoleNotFoundException(String.format("Role: %s not found!", authority)));
    }
}
