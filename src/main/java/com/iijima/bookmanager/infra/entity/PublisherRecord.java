package com.iijima.bookmanager.infra.entity;

import org.seasar.doma.*;

@Entity(immutable = true)
@Table(name = "publisher")
public class PublisherRecord {

    public PublisherRecord(Integer id, String name) {
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
