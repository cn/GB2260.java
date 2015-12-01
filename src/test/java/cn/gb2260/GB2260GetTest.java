package cn.gb2260;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GB2260GetTest {
    private static GB2260 gb2260;

    @BeforeClass
    public static void setUpClass() throws IOException {
        gb2260 = new GB2260();
    }

    @Test
    public void testGetProvince() throws Exception {
        Division data = gb2260.get("110000");
        assertEquals("北京市", data.getName());
        assertEquals("北京市", data.toString());
    }

    @Test
    public void testGetProvinces() {
        ArrayList<Division> provinces = gb2260.getProvinces();
        assertNotNull(provinces);
        assertTrue("Should not be empty", provinces.size() > 0);
    }

    @Test
    public void testGetPrefectures() throws Exception {
        ArrayList<Division> data = gb2260.getPrefectures("110000");
        assertNotNull(data);
        assertTrue("Should not be empty", data.size() > 0);
    }
    
    @Test
    public void testGetCounties() throws Exception {
        ArrayList<Division> data = gb2260.getCounties("110100");
        assertNotNull(data);
        assertTrue("Should not be empty", data.size() > 0);
    }
}
