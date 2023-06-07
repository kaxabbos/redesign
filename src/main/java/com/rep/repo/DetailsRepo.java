package com.rep.repo;

import com.rep.model.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepo extends JpaRepository<Details, Long> {
}
