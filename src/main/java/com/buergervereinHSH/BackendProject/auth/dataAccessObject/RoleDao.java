package com.buergervereinHSH.BackendProject.auth.dataAccessObject;

import com.buergervereinHSH.BackendProject.auth.model.Role;
import com.buergervereinHSH.BackendProject.auth.model.RoleName;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleDao extends CrudRepository<Role, Long> {
    Role findByName(RoleName roleName);
}
