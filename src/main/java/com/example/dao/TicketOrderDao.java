package com.example.dao;

import com.example.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketOrderDao extends JpaRepository<TicketOrder, String> {
}