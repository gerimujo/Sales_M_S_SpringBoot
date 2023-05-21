package com.bar.barproject.Reprository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.bar.barproject.Model.waiterhyr;

@Repository
public interface waiterhyrRep extends JpaRepository<waiterhyr,Integer> {
    
}
