package org.sfm.csv.cell;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class IntegerCellValueReaderTest {

	IntegerCellValueReader reader = new IntegerCellValueReader();
	@Test
	public void testReadInt() throws UnsupportedEncodingException {
		testReadInt(0);
		testReadInt(12345);
		testReadInt(-12345);
		testReadInt(Integer.MIN_VALUE);
		testReadInt(Integer.MAX_VALUE);
	}
	
	@Test
	public void testInvalidInt() throws UnsupportedEncodingException {
		final byte[] bytes = "Nan".getBytes("UTF-8");
		final char[] chars = "Nan".toCharArray();
		try {
			reader.read(bytes, 0, bytes.length, null);
			fail("Expect exception");
		} catch(ParsingException e){
			// expected
		}
		try {
			reader.read(chars, 0, chars.length);
			fail("Expect exception");
		} catch(ParsingException e){
			// expected
		}
	}

	private void testReadInt(int i) throws UnsupportedEncodingException {
		final byte[] bytes = ("_" + Integer.toString(i) + "_").getBytes("UTF-8");
		final char[] chars = ("_" + Integer.toString(i) + "_").toCharArray();
		assertEquals(i, reader.read(bytes, 1, bytes.length-2, null).intValue());
		assertEquals(i, reader.read(chars, 1, chars.length-2).intValue());
	}

}