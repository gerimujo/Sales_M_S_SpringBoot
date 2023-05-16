package com.bar.barproject.ReprositoryWaiter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface WaiterRep extends JpaRepository<waiter,Integer>{
    List<waiter>  findByName(String name);
}
