package com.wingedfish.eagles.metadata.mappers.dynamic;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wingedfish on 2017/2/23.
 */
@Repository
public interface DynamicQueryMapper {

    List<Map<String,Object>> querySql(String value);
}
