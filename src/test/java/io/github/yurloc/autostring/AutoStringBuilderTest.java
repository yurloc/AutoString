package io.github.yurloc.autostring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class AutoStringBuilderTest {

    @AutoString
    private final int intField = 123;
    @AutoString
    String strField = "hello";
    @AutoString
    protected Object objField = null;

    @Test
    public void testSimpleUsage() {
        assertThat(this.toString()).isEqualTo("AutoStringBuilderTest[intField=123,strField=hello,objField=null]");
    }

    @Override
    public String toString() {
        return AutoStringBuilder.build(this);
    }
}
