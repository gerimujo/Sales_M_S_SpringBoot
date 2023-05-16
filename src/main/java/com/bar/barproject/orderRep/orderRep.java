package com.bar.barproject.orderRep;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface orderRep extends JpaRepository<orders,Integer> {
    
    List<orders> findByIduser(Integer iduser);

    @Query("SELECT DISTINCT name FROM orders")
     List<String> findDistinctColumnValues();

     List<orders> findByName(String name);

     @Query("SELECT o FROM orders o WHERE o.name = :name")
List<orders> findByName1(@Param("name") String name);
    
}
