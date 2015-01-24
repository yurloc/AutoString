package io.github.yurloc.autostring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class BeanPropertiesSelectionTest {

    @Test
    public void testGetterDetection() {
        assertThat(new HasNoValidGetter().toString()).isEqualTo("HasNoValidGetter[]");
    }

    @Test
    public void testGetter() {
        assertThat(new HasGetter().toString()).isEqualTo("HasGetter[name=x]");
    }

    @Test
    public void testGetterThrowingException() {
        // TODO implement test
    }

    @AutoStringConfig(selectionStrategy = SelectionStrategy.BEAN_PROPERTIES)
    private static class HasNoValidGetter {

        // does not begin with "get"
        public String ggetValue() {
            return null;
        }

        // wrong capitalization
        public String getname() {
            return null;
        }

        // missing property name
        public String get() {
            return null;
        }

        // not public: protected
        protected String getProtected() {
            return null;
        }

        // not public: package protected
        String getPackageProtected() {
            return null;
        }

        // not public: private
        private String getPrivate() {
            return null;
        }

        // has parameters
        public String getWithIntParameter(int index) {
            return null;
        }

        // is void
        public void getVoid() {
        }

        @Override
        public String toString() {
            return AutoStringBuilder.toString(this);
        }
    }

    @AutoStringConfig(selectionStrategy = SelectionStrategy.BEAN_PROPERTIES)
    private static class HasGetter {

        private int internalValue = 111;

        public String getName() {
            return "x";
        }

        @Override
        public String toString() {
            return AutoStringBuilder.toString(this);
        }
    }
}
