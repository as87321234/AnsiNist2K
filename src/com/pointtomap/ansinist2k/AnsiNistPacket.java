package com.pointtomap.ansinist2k;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;

public class AnsiNistPacket {

	private SortedMap<Integer, Object> recordTypeMap = new TreeMap<Integer, Object>();

	private String buffer;

	private AnsiNistDecoder ansiNistDecoder;

	public AnsiNistPacket() {

		super();

	}

	/**
	 * 
	 * Construct a NIST packet from an input stream
	 * 
	 * @param ins
	 * @throws Exception
	 * 
	 */

	public AnsiNistPacket(String filePath) throws Exception {

		byte[] bFile = Files.readAllBytes(Paths.get(filePath));

		loadNistPackFromInputStream(bFile);
	}

	/**
	 * 
	 * Construct a NIST packet from an input stream
	 * 
	 * @param ins
	 * @throws Exception
	 * 
	 */
	public AnsiNistPacket(InputStream ins) throws Exception {

		loadNistPackFromInputStream(ins);

	}

	public void loadNistPackFromInputStream(InputStream ins) throws Exception {

		ByteArrayOutputStream bous = new ByteArrayOutputStream();

		IOUtils.copy(ins, bous);

		loadNistPackFromInputStream(bous.toByteArray());

	}

	public void loadNistPackFromInputStream(byte[] bFile) throws Exception {

		buffer = new String(bFile, StandardCharsets.ISO_8859_1);

		// Instantiate a NIST Pack decoder and load byte array in memory
		ansiNistDecoder = new AnsiNistDecoder(buffer, this);

	}

	// Record Type

	@SuppressWarnings("unchecked")
	public SortedMap<Integer, Object> getMapLevel(SortedMap<Integer, Object> nextLevel, Integer key) {

		return (SortedMap<Integer, Object>) nextLevel.get(key);

	}

	public Object getMapLevelObject(SortedMap<Integer, Object> nextLevel, Integer key) {

		return nextLevel.get(key);

	}

	@SuppressWarnings("unchecked")
	public SortedMap<Integer, Object> addMapLevel(SortedMap<Integer, Object> map, Integer key) throws Exception {

		SortedMap<Integer, Object> node = (SortedMap<Integer, Object>) map.get(key);

		if (node != null) {

			return node;
		} else {
			SortedMap<Integer, Object> aNode = new TreeMap<Integer, Object>();
			map.put(key, aNode);
			return aNode;
		}

	}

	public String findItem(int rectype, int recindx, int fieldId, int subFieldId, int itemId) throws Exception {

		String key = String.format("%d.%d.%d.%d.%d", rectype, recindx, fieldId, subFieldId, itemId);
		return findItem(key);
	}

	@SuppressWarnings("unchecked")
	public String findItem(String key) throws Exception {

		List<Integer> keyLevel = parseKey(key);

		SortedMap<Integer, Object> nextLevel = recordTypeMap;

		for (int i = 0; i < keyLevel.size(); i++) {

			try {
				nextLevel = (SortedMap<Integer, Object>) getMapLevelObject(nextLevel, keyLevel.get(i));

				if (nextLevel == null) {

					return null;

				}

			} catch (RuntimeException e) {

				String value = (String) nextLevel.get(keyLevel.get(4));
				return value;
			}

		}

		throw new Exception(String.format("Key %s not found", key));

	}

	public void createItem(String value, Integer rectype, Integer recindx, Integer fieldId, Integer subFieldId,
			Integer itemId) throws Exception {

		String key = String.format("%d.%d.%d.%d.%d", rectype, recindx, fieldId, subFieldId, itemId);

		createItem(value, key);

	}

	@SuppressWarnings("unchecked")
	public void createItem(String value, String key) throws Exception {

		List<Integer> keyLevel = parseKey(key);

		SortedMap<Integer, Object> nextLevel = recordTypeMap;

		for (int i = 0; i < keyLevel.size(); i++) {

			if (getMapLevelObject(nextLevel, keyLevel.get(i)) instanceof byte[]) {

				throw new Exception(String.format("Key %s does exist", key));
			}

			if (getMapLevelObject(nextLevel, keyLevel.get(i)) == null && i < 4) {

				SortedMap<Integer, Object> aNode = new TreeMap<Integer, Object>();

				nextLevel.put(keyLevel.get(i), aNode);
				nextLevel = aNode;

			} else if (getMapLevelObject(nextLevel, keyLevel.get(i)) != null && i < 4) {

				nextLevel = (SortedMap<Integer, Object>) getMapLevelObject(nextLevel, keyLevel.get(i));

			} else {

				nextLevel.put(keyLevel.get(i), value);

			}

		}

	}

	public void setItem(String value, int rectype, int recindx, int fieldId, int subFieldId, int itemId)
			throws Exception {

		String key = String.format("%d.%d.%d.%d.%d", rectype, recindx, fieldId, subFieldId, itemId);
		setItem(value, key);
	}

	public void setItem(String value, String key) throws Exception {

		List<Integer> keyLevel = parseKey(key);

		SortedMap<Integer, Object> nextLevel = recordTypeMap;

		for (int i = 0; i < keyLevel.size(); i++) {

			if (getMapLevelObject(nextLevel, keyLevel.get(i)) == null) {

				throw new Exception("Key does not exists");

			}

			if (getMapLevelObject(nextLevel, keyLevel.get(i)) instanceof String) {

				nextLevel.put(keyLevel.get(4), value);

			} else {
				nextLevel = getMapLevel(nextLevel, keyLevel.get(i));
			}
		}

	}

	private List<Integer> parseKey(String key) {

		String[] tokens = key.split("\\.");

		// Verify that the key is 5 level
		Assert.assertEquals(5, tokens.length);

		List<Integer> keyLevel = new ArrayList<Integer>();

		for (int i = 0; i < tokens.length; i++) {

			keyLevel.add(Integer.parseInt(tokens[i]));
		}

		return keyLevel;
	}

	public int getRecordTypeCount(int rectype) {

		@SuppressWarnings("unchecked")
		SortedMap<Integer, Object> map = (SortedMap<Integer, Object>) this.recordTypeMap.get(rectype);

		if (map == null) {
			return 0;
		} else {
			return map.size();
		}
	}

	public String retrieveItem(int recordType, int recordIndex, int field, int subfield, int item) throws Exception {
		return new String(findItem(String.format("%d.%d.%d.%d.%d", recordType, recordIndex, field, subfield, item)));

	}

	@SuppressWarnings("unchecked")
	public int getNextField(int rectype, int recordindx, int fieldId) {

		SortedMap<Integer, Object> map = (SortedMap<Integer, Object>) this.recordTypeMap.get(rectype);
		map = (SortedMap<Integer, Object>) map.get(recordindx);

		final Integer[] fieldIdResponse = new Integer[] { 0 };
		final boolean[] isNext = new boolean[] { false };

		try {
			map.forEach((k, value) -> {

				if (isNext[0]) {
					fieldIdResponse[0] = k;

					throw new RuntimeException("");

				}

				if (k == fieldId) {
					isNext[0] = true;
				}

			});
		} catch (Exception e) {
			// continue
		}

		return fieldIdResponse[0];

	}

	@SuppressWarnings("unchecked")
	public int numItems(int rectype, int recordindx, int fieldId, int subFieldId) {
		SortedMap<Integer, Object> map = (SortedMap<Integer, Object>) this.recordTypeMap.get(rectype);
		map = (SortedMap<Integer, Object>) map.get(recordindx);
		map = (SortedMap<Integer, Object>) map.get(fieldId);
		map = (SortedMap<Integer, Object>) map.get(subFieldId);

		return map.size();
	}

	@SuppressWarnings("unchecked")
	public int numSubfields(int rectype, int recordindx, int fieldId) {

		SortedMap<Integer, Object> map = (SortedMap<Integer, Object>) this.recordTypeMap.get(rectype);
		map = (SortedMap<Integer, Object>) map.get(recordindx);
		map = (SortedMap<Integer, Object>) map.get(fieldId);

		return map.size();
	}

	public SortedSet<Integer> getRectypeList() {

		SortedMap<Integer, Object> map = recordTypeMap;
		SortedSet<Integer> rectypeSet = new TreeSet<Integer>();

		map.forEach((k, n) -> {

			rectypeSet.add(k);
		});

		return rectypeSet;
	}

	// Serialize NIST Pack to a Byte Array
	public byte[] serialize() {

		return ansiNistDecoder.serialize();
	}

}
