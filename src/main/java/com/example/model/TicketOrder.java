package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticketorder")
public class TicketOrder {
    @Id
    @Column(name = "orderid")
    private String orderid;
    @Column(name = "lineid")
    private String lineid;
    @Column(name = "planeid")
    private String planeid;
    @Column(name = "id")
    private String id;
}
