package com.bar.barproject.Reprository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bar.barproject.Model.drinks;

@Repository
public interface DrinkRep extends JpaRepository<drinks,Integer>{
    
}