package com.iijima.bookmanager.infra.dao;

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

    @Insert
    Result<BookRecord> insert(BookRecord bookRecord);

}
