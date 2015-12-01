package cn.gb.gb2260;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GB2260GetTest {
    private static GB2260 gb2260;

    @BeforeClass
    public static void setUpClass() throws IOException {
        gb2260 = new GB2260();
        System.out.println("@BeforeClass setUpClass");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("@AfterClass tearDownClass");
    }

    @Test
    public void testGetProvince() throws IOException {
        Division data = gb2260.findByCode("110000");
        assertEquals("北京市", data.getName());
        assertEquals("北京市", data.toString());
    }
}
