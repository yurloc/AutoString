package io.github.yurloc.autostring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class AutoStringConfigTest {

    @Test
    public void testClassDisplayName() {
        assertThat(new Dummy().toString()).isEqualTo("!!! SomethingElse: [field=value]");
    }

    @AutoStringConfig(displayName = "!!! SomethingElse: ")
    private static class Dummy {

        @AutoString
        private String field = "value";

        @Override
        public String toString() {
            return AutoStringBuilder.toString(this);
        }

    }
}
