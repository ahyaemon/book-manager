package com.iijima.bookmanager.infra.dao;

import com.iijima.bookmanager.infra.entity.PublisherRecord;
import org.seasar.doma.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@AnnotateWith(annotations = {
        @Annotation(target = AnnotationTarget.CLASS, type = Component.class),
        @Annotation(target = AnnotationTarget.CONSTRUCTOR, type = Autowired.class) })
@Dao
public interface PublisherDao {

    @Select
    List<PublisherRecord> select();

}
