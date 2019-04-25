package com.iijima.bookmanager.infra.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

@Entity
public class BookSummary {

    public BookSummary() {}

    public BookSummary(Integer id, String title, Integer authorId, String authorName, Integer publisherId, String publisherName) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.authorName = authorName;
        this.publisherId = publisherId;
        this.publisherName = publisherName;
    }

    @Column(name = "id")
    public Integer id;

    @Column(name = "title")
    public String title;

    @Column(name = "author_id")
    public Integer authorId;

    @Column(name = "author_name")
    public String authorName;

    @Column(name = "publisher_id")
    public Integer publisherId;

    @Column(name = "publisher_name")
    public String publisherName;

}
