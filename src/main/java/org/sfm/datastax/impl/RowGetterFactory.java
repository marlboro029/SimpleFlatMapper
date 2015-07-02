package org.sfm.datastax.impl;

import com.datastax.driver.core.GettableData;
import org.sfm.datastax.DatastaxColumnKey;
import org.sfm.map.ColumnDefinition;
import org.sfm.map.GetterFactory;
import org.sfm.reflect.Getter;
import org.sfm.reflect.TypeHelper;

import java.lang.reflect.Type;

public class RowGetterFactory implements GetterFactory<GettableData, DatastaxColumnKey> {

    @SuppressWarnings("unchecked")
    @Override
    public <P> Getter<GettableData, P> newGetter(Type target, DatastaxColumnKey key, ColumnDefinition<?, ?> columnDefinition) {
        if (TypeHelper.isClass(target, Long.class) || TypeHelper.isClass(target, long.class)) {
            return (Getter<GettableData, P>) new DatastaxLongGetter(key.getIndex());
        }
        if (TypeHelper.isClass(target, Integer.class) || TypeHelper.isClass(target, int.class)) {
            return (Getter<GettableData, P>) new DatastaxIntegerGetter(key.getIndex());
        }
        if (TypeHelper.isClass(target, Float.class) || TypeHelper.isClass(target, float.class)) {
            return (Getter<GettableData, P>) new DatastaxFloatGetter(key.getIndex());
        }
        if (TypeHelper.isClass(target, Double.class) || TypeHelper.isClass(target, double.class)) {
            return (Getter<GettableData, P>) new DatastaxDoubleGetter(key.getIndex());
        }
        if (TypeHelper.isClass(target, String.class)) {
            return (Getter<GettableData, P>) new DatastaxStringGetter(key.getIndex());
        }
        return null;
    }
}