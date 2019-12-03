package com.sand.core.collection.providers;

import com.sand.common.collection.PageCollection;
import com.sand.core.repositories.SQL;

import java.util.Date;
import java.util.Map;

public class PageCollectionSqlProvider {
    private String getEntityTable() {
        return "T_PAGE_COLLECTION";
    }

    public String save(PageCollection pageCollection) {
        return new SQL()
                .INSERT_INTO(getEntityTable())
                .VALUES("REMOTE_ADDR", "#{remoteAddr}")
                .VALUES("REFERER", "#{referer}")
                .VALUES("URL", "#{url}")
                .VALUES("METHOD_NAME", "#{methodName}")
                .VALUES_IF("START_TIME", "#{startTime}", pageCollection.getStartTime() != null)
                .VALUES_IF("END_TIME", "#{endTime}", pageCollection.getEndTime() != null)
                .VALUES_IF("SPEND_TIME", "#{spendTime}", pageCollection.getSpendTime() != null)
                .VALUES_IF("CREATE_TIME", "#{createTime}", pageCollection.getCreateTime() != null)
                .VALUES_IF("UPDATE_TIME", "#{updateTime}", pageCollection.getUpdateTime() != null)
                .toString();
    }

    public String findCollectionsByDuration(Map<String, Object> params){
        Date fromDate = (Date)params.get("fromDate");
        Date toDate = (Date)params.get("toDate");
        return new SQL()
                .SELECT("*")
                .FROM(getEntityTable())
                .WHERE_IF("CREATE_TIME >= #{fromDate}", fromDate != null)
                .WHERE_IF("CREATE_TIME < #{toDate}", toDate != null)
                .toString();
    }
}
