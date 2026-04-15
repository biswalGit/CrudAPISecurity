package com.pit.crudapp.repository;

import com.pit.crudapp.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudRepository extends JpaRepository<Hospital, Integer> {
}
