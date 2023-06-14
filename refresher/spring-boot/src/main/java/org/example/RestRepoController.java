package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "dev-ops", path = "dev-ops")
public interface RestRepoController extends JpaRepository<DevOpsModel, Long> {
}
