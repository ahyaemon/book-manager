package com.iijima.bookmanager.infra.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

@Entity
public class BookSummary {

    @Column(name = "id")
    public Integer id;

    @Column(name = "title")
    public String title;

    @Column(name = "author_name")
    public String author;

    @Column(name = "publisher_name")
    public String publisher;

}
