package com.iijima.bookmanager.infra.dao;

import com.iijima.bookmanager.infra.entity.AuthorRecord;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@AnnotateWith(annotations = {
//        @Annotation(target = AnnotationTarget.CLASS, type = Component.class),
//        @Annotation(target = AnnotationTarget.CONSTRUCTOR, type = Autowired.class) })
@ConfigAutowireable
@Dao
public interface AuthorDao {

    @Select
    List<AuthorRecord> select();

}
