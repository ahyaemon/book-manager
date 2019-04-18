package com.iijima.bookmanager.infra.entity;

import org.seasar.doma.*;

@Entity
public class AuthorRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "name")
    public String name;

}
