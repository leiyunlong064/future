package com.sand.core.collection.mappers;

import com.sand.common.collection.PageCollection;
import com.sand.core.collection.providers.PageCollectionSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface PageCollectionMapper {
    @InsertProvider(type = PageCollectionSqlProvider.class, method = "save")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int save(PageCollection pageCollection);

    @SelectProvider(type = PageCollectionSqlProvider.class, method = "findCollectionsByDuration")
    List<PageCollection> findCollectionsByDuration(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}
