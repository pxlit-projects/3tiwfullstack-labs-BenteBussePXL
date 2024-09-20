package be.pxl.miccroservices.organizationservice.repository;

import be.pxl.miccroservices.organizationservice.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
