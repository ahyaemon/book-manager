package com.iijima.bookmanager.infra.dao;

import com.iijima.bookmanager.domain.entity.Book;
import com.iijima.bookmanager.infra.entity.BookRecord;
import com.iijima.bookmanager.infra.entity.BookSummary;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;
import java.util.List;

@ConfigAutowireable
@Dao
public interface BookDao {

    @Select
    List<BookSummary> selectBookSummaries();

    @Select
    List<BookSummary> selectBookSummariesBy(String title, Integer authorId, Integer publisherId);

    @Select
    int countSameBook(Book book);

    @Insert
    Result<BookRecord> insert(BookRecord bookRecord);

    @Update
    Result<BookRecord> update(BookRecord bookRecord);

    @Delete(sqlFile = true)
    int delete(Integer id);

}
