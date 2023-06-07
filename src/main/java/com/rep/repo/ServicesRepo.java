package com.rep.repo;

import com.rep.model.Orderings;
import com.rep.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepo extends JpaRepository<Services, Long> {
}
