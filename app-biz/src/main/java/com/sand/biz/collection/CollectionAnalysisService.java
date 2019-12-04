package com.sand.biz.collection;

import com.sand.biz.utils.DateUtil;
import com.sand.common.collection.Geo;
import com.sand.common.collection.GeoViewCollection;
import com.sand.common.collection.PageCollection;
import com.sand.common.enums.DurationUnit;
import com.sand.core.collection.mappers.GeoViewCollectionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectionAnalysisService {
    private Logger logger = LoggerFactory.getLogger(CollectionAnalysisService.class);

    @Autowired
    private PageCollectionService pageCollectionService;
    @Autowired
    private GeoViewCollectionMapper geoViewCollectionMapper;

    public void analyzeByPerDay(){
        Date now = new Date();
        Date fromDate = DateUtil.trimDate(DateUtil.minusDays(now, 1));
        Date toDate = DateUtil.trimToEndOfDate(DateUtil.minusDays(now, 1));
        Map<Geo,Long> geoMap = new HashMap<>();
        List<PageCollection> pageCollectionList = pageCollectionService.getCollectionByDuration(fromDate, toDate);
        for (PageCollection collection : pageCollectionList){
            Geo geo = convertIpToGeo(collection.getRemoteAddr());
            Long viewCount = geoMap.getOrDefault(geo,0L);
            geoMap.put(geo, ++viewCount);
        }
        for(Geo geo: geoMap.keySet()){
            Long viewCount = geoMap.get(geo);
            createGeoViewCollection(geo, viewCount, fromDate);
        }

    }

    //todo add method
    private Geo convertIpToGeo(String ip){
        return new Geo();
    }

    private void createGeoViewCollection(Geo geo, Long viewCount, Date collectDate){
        GeoViewCollection viewCollection = new GeoViewCollection()
                .setViewCount(viewCount)
                .setCollectDate(collectDate)
                .setDurationUnit(DurationUnit.DAY)
                .setProvince(geo.getProvince())
                .setCity(geo.getCity());
        geoViewCollectionMapper.create(viewCollection);
    }

    private GeoViewCollection getGeoViewCollection(String province, String city, Date collectDate){
        return geoViewCollectionMapper.findGeoView(province, city, collectDate);
    }

}
