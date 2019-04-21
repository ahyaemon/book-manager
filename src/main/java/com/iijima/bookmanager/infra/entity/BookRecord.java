package com.iijima.bookmanager.infra.entity;

import org.seasar.doma.*;

@Entity(immutable = true)
@Table(name = "book")
public class BookRecord {

    public BookRecord(Integer id, String title, Integer author_id, Integer publisher_id) {
        this.id = id;
        this. title = title;
        this.author_id = author_id;
        this.publisher_id = publisher_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    final public Integer id;

    @Column(name = "title")
    final public String title;

    @Column(name = "author_id")
    final public Integer author_id;

    @Column(name = "publisher_id")
    final public Integer publisher_id;

}
