package com.iijima.bookmanager.infra.entity;

import org.seasar.doma.*;

@Entity
public class BookRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "title")
    public String title;

    @Column(name = "author_id")
    public Integer author_id;

    @Column(name = "publisher_id")
    public Integer publisher_id;

}
