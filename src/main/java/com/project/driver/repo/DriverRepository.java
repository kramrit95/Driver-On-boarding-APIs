package com.project.driver.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.driver.Entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

    Driver findByEmail(String email);
}