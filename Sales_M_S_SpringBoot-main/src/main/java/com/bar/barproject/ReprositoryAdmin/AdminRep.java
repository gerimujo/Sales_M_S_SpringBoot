package com.bar.barproject.ReprositoryAdmin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRep extends JpaRepository<admin, Integer> {
    List<admin> findByUser(String user);
}
