package org.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeveloperRepo extends JpaRepository<DevModel, Long> {

    @Query("from DevModel where dev_name like %?1% order by id")
    List<DevModel> getDataByNameWithQuery(String firstParam);

    Page<DevModel> findAll(Pageable page);
}
