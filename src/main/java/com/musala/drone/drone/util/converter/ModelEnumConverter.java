package com.musala.drone.drone.util.converter;

import com.musala.drone.drone.repository.enums.ModelEnum;
import org.springframework.core.convert.converter.Converter;

public class ModelEnumConverter implements Converter<String, ModelEnum> {
    @Override
    public ModelEnum convert(String source) {
        return ModelEnum.valueOf(source);
    }
}
