package laace.swc3man2.repositories;

import laace.swc3man2.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<RoleModel, Integer> {
    RoleModel findByRole(String role);
}
