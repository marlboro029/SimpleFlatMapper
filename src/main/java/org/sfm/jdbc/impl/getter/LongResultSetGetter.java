package org.sfm.jdbc.impl.getter;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.sfm.reflect.Getter;
import org.sfm.reflect.primitive.LongGetter;

public final class LongResultSetGetter implements LongGetter<ResultSet>,
		Getter<ResultSet, Long> {

	private final int column;

	public LongResultSetGetter(final int column) {
		this.column = column;
	}

	@Override
	public long getLong(final ResultSet target) throws SQLException {
		return target.getLong(column);
	}

	@Override
	public Long get(final ResultSet target) throws Exception {
		final long l = getLong(target);
		if (target.wasNull()) {
			return null;
		} else {
			return Long.valueOf(l);
		}
	}
}