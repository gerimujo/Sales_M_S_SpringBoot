package com.bar.barproject.ReprositoryDrinks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRep extends JpaRepository<drinks,Integer>{
    
}

