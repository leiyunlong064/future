package com.sand.biz.collection;

import com.sand.common.collection.PageCollection;
import com.sand.core.collection.mappers.PageCollectionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PageCollectionService {
    private Logger logger = LoggerFactory.getLogger(PageCollectionService.class);

    @Autowired
    private PageCollectionMapper pageCollectionMapper;

    public void create(PageCollection pageCollection){
        pageCollectionMapper.save(pageCollection);
    }

    public List<PageCollection> getCollectionByDuration(Date fromDate, Date toDate){
        return pageCollectionMapper.findCollectionsByDuration(fromDate, toDate);
    }
}
