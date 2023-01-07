package com.example.dao;

import com.example.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneDao extends JpaRepository<Plane, String> {
}
