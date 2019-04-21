package com.iijima.bookmanager.infra.entity;

import org.seasar.doma.*;

@Entity(immutable = true)
@Table(name = "author")
public class AuthorRecord {

    public AuthorRecord(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    final public Integer id;

    @Column(name = "name")
    final public String name;

}
