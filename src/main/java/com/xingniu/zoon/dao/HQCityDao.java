package com.xingniu.zoon.dao;

import com.xingniu.zoon.model.HQCity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author luojian
 * @version 1.0
 * @ClassName: HQCityDao
 * @Reason: TODO ADD REASON(可选)
 * @date: 2018年08月30日 15:34
 * @company:
 * @since JDK 1.8
 */
@Mapper
public interface HQCityDao extends tk.mybatis.mapper.common.Mapper<HQCity>{


    HQCity getByCityId(@Param("cityId") String cityId);
}
