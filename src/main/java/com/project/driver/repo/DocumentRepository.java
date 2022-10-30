package com.project.driver.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.driver.Entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {
}

