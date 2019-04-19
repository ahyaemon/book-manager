package com.iijima.bookmanager.infra.dao;

import com.iijima.bookmanager.infra.entity.BookSummary;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface BookDao {

    @Select
    List<BookSummary> selectBookSummaries();

}
