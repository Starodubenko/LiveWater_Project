package com.epam.star.entity;

import org.reflections.Reflections;

import java.util.*;

public class UtilEntity {

    private static SortedSet<String> result = new TreeSet<>();

    public static SortedSet<String> getEntityNames (){
        Reflections reflections = new Reflections(UtilEntity.class.getPackage().getName());
        Set<Class<?>> entities = reflections.getTypesAnnotatedWith(MappedEntityForAdmin.class);

        for (Class<?> entityClass : entities) {
            MappedEntityForAdmin mappedDao = entityClass.getAnnotation(MappedEntityForAdmin.class);
            result.add(mappedDao.value().toLowerCase());
        }

        return result;
    }
}
