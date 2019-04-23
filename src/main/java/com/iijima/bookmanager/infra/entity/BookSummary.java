package com.iijima.bookmanager.infra.entity;

import com.iijima.bookmanager.domain.entity.Book;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;

@Entity
public class BookSummary {

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
