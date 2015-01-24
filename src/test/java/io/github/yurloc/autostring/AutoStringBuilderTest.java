package io.github.yurloc.autostring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AutoStringBuilderTest {

    @AutoString
    private final int intField = 123;
    @AutoString
    String strField = "hello";
    @AutoString
    protected Object objField = null;

    @Test
    public void testSomeMethod() {
        assertEquals("AutoStringBuilderTest[intField=123,strField=hello,objField=null]", this.toString());
    }

    @Override
    public String toString() {
        return AutoStringBuilder.build(this);
    }
}
