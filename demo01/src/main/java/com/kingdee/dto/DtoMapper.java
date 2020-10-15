package com.kingdee.dto;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * description:
 *
 * @author qy
 * @version v1.0
 * @date Created in 2020/10/15
 */

public class DtoMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        ConverterFactory converterFactory = factory.getConverterFactory();
    }

    @Override
    protected void configureFactoryBuilder(DefaultMapperFactory.Builder factoryBuilder) {
        factoryBuilder.mapNulls(false);
    }
}
