package com.bar.barproject.Adminhyrreprository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface adminhyrRep extends JpaRepository<adminhyr,Integer>{
    
}
