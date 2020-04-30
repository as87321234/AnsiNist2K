package com.pointtomap.ansinist2k;

import java.io.File;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class AnsiNistPacketTest {

	private static Logger log = LoggerFactory.getLogger(AnsiNistDecoder.class);


	@Test
	public void checkFileEncoding() throws Exception {

		Assert.assertEquals(null, "ISO-8859-1", System.getProperty("file.encoding"));
	}

	@Test
	public void verifyImage() throws Exception {

		File f = new File("resources/TestData/elaine.eft");
		AnsiNistPacket iNist = new AnsiNistPacket(f.getCanonicalPath());

		byte[] faceImage = iNist.findItem("10.1.999.1.1").getBytes();

		Assert.assertEquals(null, "9bf213eb6e5a3dc054890acac06b7220d9fa4d5b", DigestUtils.sha1Hex(faceImage));

	}

	@Test
	public void verifyRectypeCalculation() throws Exception {

		File f = new File("resources/TestData/elaine.eft");
		AnsiNistPacket iNist = new AnsiNistPacket(f.getCanonicalPath());

		iNist.setItem("SNDKR-VFS+", "1.1.8.1.1");

		iNist.setItem("SNDKR-VFS+", "2.1.904.1.3");
		iNist.setItem("20190812171047Z+", "2.1.904.1.4");
		iNist.setItem("CrossMatch++", "2.1.8067.1.1");
		iNist.setItem("L SCAN GUARDIAN L++", "2.1.8067.1.2");

		iNist.setItem("SNDKR-VFS+", "10.1.4.1.1");

		iNist.setItem("SNDKR-VFS+", "14.1.4.1.1");
		iNist.setItem("SNDKR-VFS+", "14.2.4.1.1");
		iNist.setItem("SNDKR-VFS+", "14.3.4.1.1");

		iNist.loadNistPackFromInputStream(iNist.serialize());

		Assert.assertEquals(null, "214", iNist.findItem("1.1.1.1.1"));

		Assert.assertEquals(null, "440", iNist.findItem("2.1.1.1.1"));

		Assert.assertEquals(null, "11090", iNist.findItem("10.1.1.1.1"));

		Assert.assertEquals(null, "120508", iNist.findItem("14.1.1.1.1"));
		Assert.assertEquals(null, "134554", iNist.findItem("14.2.1.1.1"));
		Assert.assertEquals(null, "93057", iNist.findItem("14.3.1.1.1"));

	}

	@Test
	public void testWriteMem() throws Exception {

		File f = new File("resources/TestData/elaine.eft");
		AnsiNistPacket iNist = new AnsiNistPacket(f.getCanonicalPath());

		byte[] nistByte = iNist.serialize();

		Assert.assertEquals(null, "082b637aa41110a0ad9bcc7784d58703d4c49277", DigestUtils.sha1Hex(nistByte));

	}

	@Test
	public void createItem() throws Exception {
		AnsiNistPacket iNist = new AnsiNistPacket();

		String key = "1.1.1.1.2";
		iNist.createItem("Test", key);
		iNist.createItem("Test", 1, 1, 1, 1, 3);
	}

	@Test
	public void createItemExist() {

		AnsiNistPacket iNist = new AnsiNistPacket();

		String key = "1.1.1.1.2";

		try {

			iNist.createItem("Test", key);
			iNist.createItem("Test", key);

		} catch (Exception e) {

			Assert.assertEquals(null, "Key 1.1.1.1.1 does exist", e.getMessage());
		}

	}

	@Test
	public void findItem() throws Exception {

		AnsiNistPacket iNist = new AnsiNistPacket();

		String key = "1.1.1.1.2";

		iNist.createItem("Test", key);

		String value = iNist.findItem(key);
		Assert.assertEquals(null, "Test", new String(value));

		value = iNist.findItem(1, 1, 1, 1, 2);
		Assert.assertEquals(null, "Test", new String(value));

	}

	@Test
	public void findItemNotExists() throws Exception {

		AnsiNistPacket iNist = new AnsiNistPacket();

		String key = "1.1.1.1.2";

		String value = iNist.findItem(key);
		Assert.assertEquals(null, null, value);

	}

	@Test
	public void setItem() throws Exception {

		AnsiNistPacket iNist = new AnsiNistPacket();

		String key = "1.1.1.1.2";

		iNist.createItem("Test", key);
		iNist.setItem("Changed", key);

		String value = iNist.findItem(key);

		Assert.assertEquals(null, "Changed", new String(value));

	}

}
