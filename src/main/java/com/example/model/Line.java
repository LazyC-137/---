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
@Table(name = "line")
public class Line {
    @Id
    @Column(name = "lineid")
    private String lineid;
    @Column(name = "beginplace")
    private String beginplace;
    @Column(name = "endplace")
    private String endplace;
}
