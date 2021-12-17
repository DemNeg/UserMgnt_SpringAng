package kuma.ma.UserMngBackend.repositories;

import kuma.ma.UserMngBackend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByRoleName(String roleName);
}
