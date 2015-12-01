package cn.gb2260;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GB2260GetTest {
    private static GB2260 gb2260;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setUpClass() throws IOException {
        gb2260 = new GB2260();
    }

    @Test
    public void testGetProvince() {
        Division data = gb2260.getDivision("110000");
        assertEquals("北京市", data.getName());
        assertEquals("北京市", data.toString());
        assertEquals(Revision.V2014.getCode(), data.getRevision());
        assertEquals("110000", data.getCode());
    }

    @Test
    public void testGetPrefecture() {
        Division data = gb2260.getDivision("110100");
        assertEquals("市辖区", data.getName());
        assertEquals("北京市", data.getProvince());
        assertEquals("北京市 市辖区", data.toString());
    }

    @Test
    public void testGetCounty() {
        Division data = gb2260.getDivision("110101");
        assertEquals("东城区", data.getName());
        assertEquals("北京市", data.getProvince());
        assertEquals("市辖区", data.getPrefecture());
        assertEquals("北京市 市辖区 东城区", data.toString());
    }

    @Test
    public void testNull() {
        assertNull(gb2260.getDivision("999999"));
    }

    @Test
    public void testInvalidCodeLength() {
        thrown.expect(InvalidCodeException.class);
        thrown.expectMessage("Invalid code");
        gb2260.getDivision("2207248");
    }

    @Test
    public void testInvalidCodeLength2() {
        thrown.expect(InvalidCodeException.class);
        thrown.expectMessage("Invalid code");
        gb2260.getDivision("2");
    }

    @Test
    public void testInvalidProvinceCode() {
        thrown.expect(InvalidCodeException.class);
        thrown.expectMessage("Province code not found");
        gb2260.getPrefectures("990000");
    }

    @Test
    public void testInvalidProvinceCode2() {
        thrown.expect(InvalidCodeException.class);
        thrown.expectMessage("Invalid code");
        gb2260.getDivision("99");
    }

    @Test
    public void testInvalidPrefectureCode() {
        thrown.expect(InvalidCodeException.class);
        thrown.expectMessage("Invalid code");
        gb2260.getDivision("111");
    }

    @Test
    public void testInvalidPrefectureCode2() {
        thrown.expect(InvalidCodeException.class);
        thrown.expectMessage("Invalid code");
        gb2260.getDivision("1109");
    }

    @Test
    public void testInvalidPrefectureCode3() {
        thrown.expect(InvalidCodeException.class);
        thrown.expectMessage("Prefecture code not found");
        gb2260.getCounties("999900");
    }

    @Test
    public void testInvalidCountyCode() {
        thrown.expect(InvalidCodeException.class);
        thrown.expectMessage("Invalid code");
        gb2260.getDivision("11019");
    }
}
