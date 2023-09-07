package com.musala.drone.drone.util.converter;

import com.musala.drone.drone.repository.enums.StateEnum;
import org.springframework.core.convert.converter.Converter;

public class StateEnumConverter implements Converter<String, StateEnum> {
    @Override
    public StateEnum convert(String source) {
        return StateEnum.valueOf(source);
    }
}
