package io.github.yurloc.autostring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class AutoStringConfigTest {

    @Test
    public void testClassDisplayName() {
        assertThat(new Dummy().toString()).isEqualTo("!!! SomethingElse: [field=value]");
    }

    @Test
    public void testSelectAll() {
        assertThat(new SelectAllFields().toString()).isEqualTo("SelectAllFields[pvt=pvt,pkg=pkg,ptd=ptd,pub=pub]");
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

    @AutoStringConfig(selectionStrategy = SelectionStrategy.ALL)
    private static class SelectAllFields {

        private String pvt = "pvt";
        String pkg = "pkg";
        protected String ptd = "ptd";
        public String pub = "pub";

        @Override
        public String toString() {
            return AutoStringBuilder.toString(this);
        }
    }
}
