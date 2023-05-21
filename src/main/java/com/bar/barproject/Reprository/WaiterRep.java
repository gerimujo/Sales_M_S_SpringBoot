package com.bar.barproject.Reprository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bar.barproject.Model.waiter;
@Repository
public interface WaiterRep extends JpaRepository<waiter,Integer>{
    List<waiter>  findByName(String name);
}
