package com.rep.repo;

import com.rep.model.Details;
import com.rep.model.Orderings;
import com.rep.model.Users;
import com.rep.model.enums.OrderingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderingsRepo extends JpaRepository<Orderings, Long> {
    List<Orderings> findAllByStatusAndOwner_Id(OrderingStatus status, Long ownerId);
    List<Orderings> findAllByStatusNotAndOwner_Id(OrderingStatus status, Long ownerId);
    List<Orderings> findAllByStatusNot(OrderingStatus status);

}
