package com.bar.barproject.Reprository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bar.barproject.Model.admin;

@Repository
public interface AdminRep extends JpaRepository<admin, Integer> {
    List<admin> findByUser(String user);
}
