package com.bar.barproject.Reprository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bar.barproject.Model.adminhyr;

@Repository
public interface adminhyrRep extends JpaRepository<adminhyr,Integer>{
    
}
