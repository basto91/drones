package com.musala.drone.drone.service.mapper;

import java.util.List;

public interface Mapper <T,R>{

    R entityToObject(T entity);

    T objectToEntity(R object);

    List<R> entitiesToObjectList(List<T> entities);

    List<T>objectsToEntietiesList(List<R> objects);
}
