package com.bar.barproject.waiterhyrReporsitory;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface waiterhyrRep extends JpaRepository<waiterhyr,Integer> {
    
}
