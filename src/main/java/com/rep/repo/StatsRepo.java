package com.rep.repo;

import com.rep.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepo extends JpaRepository<Stats, Long> {
}
