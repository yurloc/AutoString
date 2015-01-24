package io.github.yurloc.autostring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class AutoStringTest {

    @Test
    public void testFieldDisplayName() {
        assertThat(new Dummy().toString()).isEqualTo("Dummy[ New \"name\" =1]");
    }

    private static class Dummy {

        @AutoString(displayName = " New \"name\" ")
        private String shouldBeRenamed = "1";

        @Override
        public String toString() {
            return AutoStringBuilder.toString(this);
        }
    }
}
