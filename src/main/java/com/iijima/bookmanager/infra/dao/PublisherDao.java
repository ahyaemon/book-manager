package com.iijima.bookmanager.infra.dao;

import com.iijima.bookmanager.infra.entity.PublisherRecord;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface PublisherDao {

    @Select
    List<PublisherRecord> select();

}
