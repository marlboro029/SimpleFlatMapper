package org.sfm.csv.impl.primitive;

import org.sfm.csv.CsvMapper;
import org.sfm.csv.impl.BreakDetector;
import org.sfm.csv.impl.CsvMapperCellConsumer;
import org.sfm.csv.impl.DelayedCellSetter;
import org.sfm.csv.impl.DelayedCellSetterFactory;
import org.sfm.csv.impl.cellreader.IntegerCellValueReader;
import org.sfm.reflect.primitive.IntSetter;

import java.util.Map;

public class IntDelayedCellSetterFactory<T> implements DelayedCellSetterFactory<T, Integer> {

	private final IntSetter<T> setter;
	private final IntegerCellValueReader reader;

	public IntDelayedCellSetterFactory(IntSetter<T> setter, IntegerCellValueReader reader) {
		this.setter = setter;
		this.reader = reader;
	}

	@Override
	public DelayedCellSetter<T, Integer> newCellSetter(BreakDetector breakDectector, Map<CsvMapper<?>, CsvMapperCellConsumer<?>> cellHandlers) {
		return new IntDelayedCellSetter<T>(setter, reader);
	}

    @Override
    public String toString() {
        return "IntDelayedCellSetterFactory{" +
                "setter=" + setter +
                ", reader=" + reader +
                '}';
    }
}
