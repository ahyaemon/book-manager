package com.iijima.bookmanager.infra.dao;

import com.iijima.bookmanager.infra.entity.AuthorRecord;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;

import java.util.List;

@ConfigAutowireable
@Dao
public interface AuthorDao {

    @Select
    List<AuthorRecord> select();

    @Insert
    Result<AuthorRecord> insert(AuthorRecord authorRecord);

}
