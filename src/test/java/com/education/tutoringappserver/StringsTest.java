package com.cuongnn.tutoringappserver;

import com.cuongnn.tutoringappserver.common.utils.Strings;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StringsTest {
    @Test
    public void testRefactorString() {
        Assert.assertEquals("refactor", Strings.refactor("　　refactor   "));
    }

    @Test
    public void testRefactorNotNull() {
        Assert.assertNotEquals(null, Strings.refactor(null));
    }

    @Test
    public void testRefactorNullValue() {
        Assert.assertEquals("", Strings.refactor(null));
    }

    @Test
    public void testStringNvl() {
        Assert.assertEquals("", Strings.nvl(null));
    }

    @Test
    public void testTrimAllWhiteSpace() {
        Assert.assertEquals("String", Strings.trimAllWhiteSpace("　String　  "));
    }
}
