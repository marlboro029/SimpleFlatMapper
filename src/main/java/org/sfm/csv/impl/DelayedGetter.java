package org.sfm.csv.impl;

import org.sfm.reflect.Getter;

public class DelayedGetter<T> implements Getter<CsvMapperObjectSetters<?>, T> {
	private final int index;
	
	public DelayedGetter(int index) {
		this.index = index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(CsvMapperObjectSetters<?> target) throws Exception {
		return (T) target.getDelayedCellSetter(index).consumeValue();
	}

    @Override
    public String toString() {
        return "DelayedGetter{" +
                "index=" + index +
                '}';
    }
}
