package io.github.yurloc.autostring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class BeanPropertiesSelectionTest {

    @Test
    public void testGetterDetection() {
        assertThat(new HasInvalidGettersButOne().toString())
                .isEqualTo("HasInvalidGettersButOne[onlyValidProperty=value]");
    }

    @Test
    public void testBooleanGetterDetection() {
        assertThat(new HasInvalidBooleanGettersButOne().toString())
                .isEqualTo("HasInvalidBooleanGettersButOne[onlyValidProperty=true]");
    }

    @Test
    public void testFieldsAreIgnoredUnlessAnnotated() {
        assertThat(new HasGetterAndFields().toString()).isEqualTo("HasGetterAndFields[extra=value,name=x]");
    }

    @Test
    public void testGetterThrowingException() {
        // TODO implement test
    }

    @AutoStringConfig(selectionStrategy = SelectionStrategy.BEAN_PROPERTIES)
    private static class HasInvalidGettersButOne {

        public String getOnlyValidProperty() {
            return "value";
        }

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
    private static class HasInvalidBooleanGettersButOne {

        public boolean isOnlyValidProperty() {
            return true;
        }

        // does not begin with "is"
        public boolean iisDone() {
            return true;
        }

        // wrong capitalization
        public boolean isready() {
            return true;
        }

        // missing property name
        public boolean is() {
            return true;
        }

        // not public: protected
        protected boolean getProtected() {
            return true;
        }

        // not public: package protected
        boolean getPackageProtected() {
            return true;
        }

        // not public: private
        private boolean getPrivate() {
            return true;
        }

        // has parameters
        public boolean isWithIntParameter(int index) {
            return true;
        }

        // is void
        public void isVoid() {
        }

        @Override
        public String toString() {
            return AutoStringBuilder.toString(this);
        }
    }

    @AutoStringConfig(selectionStrategy = SelectionStrategy.BEAN_PROPERTIES)
    private static class HasGetterAndFields {

        private int someField = 111;
        private String name = "should be ignored";
        public String pub = "public field";
        @AutoString
        private String extra = "value";

        public String getName() {
            return "x";
        }

        @Override
        public String toString() {
            return AutoStringBuilder.toString(this);
        }
    }
}
