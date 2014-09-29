package org.sfm.csv.cell;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class ShortCellValueReaderTest {

	ShortCellValueReader reader = new ShortCellValueReader();
	@Test
	public void testReadShort() throws UnsupportedEncodingException {
		testReadShort(0);
		testReadShort(12345);
		testReadShort(-12345);
		testReadShort(Short.MIN_VALUE);
		testReadShort(Short.MAX_VALUE);
	}
	
	@Test
	public void testInvalidShort() throws UnsupportedEncodingException {
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

	private void testReadShort(int i) throws UnsupportedEncodingException {
		final byte[] bytes = ("_" + Integer.toString(i) + "_").getBytes("UTF-8");
		final char[] chars = ("_" + Integer.toString(i) + "_").toCharArray();
		assertEquals(i, reader.read(bytes, 1, bytes.length-2, null).shortValue());
		assertEquals(i, reader.read(chars, 1, chars.length-2).shortValue());
	}

}