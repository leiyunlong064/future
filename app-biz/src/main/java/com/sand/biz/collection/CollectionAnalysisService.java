package com.sand.biz.collection;

import com.sand.biz.utils.DateUtil;
import com.sand.common.collection.PageCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CollectionAnalysisService {
    private Logger logger = LoggerFactory.getLogger(CollectionAnalysisService.class);

    @Autowired
    private PageCollectionService pageCollectionService;

    public void analyzeByPerDay(){
        Date now = new Date();
        Date fromDate = DateUtil.trimDate(DateUtil.minusDays(now, 1));
        Date toDate = DateUtil.trimToEndOfDate(DateUtil.minusDays(now, 1));
        List<PageCollection> pageCollectionList = pageCollectionService.getCollectionByDuration(fromDate, toDate);
        //todo convert ip to province and city;
        //todo city call count;
    }

}
