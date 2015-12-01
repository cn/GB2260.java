package cn.gb2260;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GB2260TreeTest {
    private static GB2260 gb2260;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setUpClass() throws IOException {
        gb2260 = new GB2260();
    }

    @Test
    public void testGetProvinces() {
        ArrayList<Division> provinces = gb2260.getProvinces();
        assertNotNull(provinces);
        assertTrue("Should not be empty", provinces.size() > 0);
    }

    @Test
    public void testGetPrefectures() {
        ArrayList<Division> data = gb2260.getPrefectures("110000");
        assertNotNull(data);
        assertTrue("Should not be empty", data.size() > 0);
    }

    @Test
    public void testGetCounties() {
        ArrayList<Division> data = gb2260.getCounties("110100");
        assertNotNull(data);
        assertTrue("Should not be empty", data.size() > 0);
    }

    @Test
    public void testCanNotGetPrefectures() {
        thrown.expect(InvalidCodeException.class);
        thrown.expectMessage("Province code not found");
        gb2260.getPrefectures("990000");
    }

    @Test
    public void testInvalidPrefectures() {
        thrown.expect(InvalidCodeException.class);
        thrown.expectMessage("Invalid province code");
        gb2260.getPrefectures("123");
    }

    @Test
    public void testCanNotGetCounties() {
        thrown.expect(InvalidCodeException.class);
        thrown.expectMessage("Prefecture code not found");
        gb2260.getCounties("110900");
    }

    @Test
    public void testInvalidCounties() {
        thrown.expect(InvalidCodeException.class);
        thrown.expectMessage("Invalid prefecture code");
        gb2260.getCounties("9");
    }
}
