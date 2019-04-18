package com.iijima.bookmanager.infra.entity;

import org.seasar.doma.*;

@Entity
public class PublisherRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "name")
    public String name;

}
