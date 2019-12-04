package com.sand.core.collection.mappers;

import com.sand.common.collection.GeoViewCollection;

import java.util.Date;
import java.util.List;

public interface GeoViewCollectionMapper {
    int create(GeoViewCollection collection);

    GeoViewCollection findGeoView(String province, String city, Date collectDate);

    List<GeoViewCollection> findGeoView(Date fromDate, Date toDate);

    int increaseViewCount(Long id);
}
