package com.example.dao;

import com.example.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineDao extends JpaRepository<Line, String> {
}
