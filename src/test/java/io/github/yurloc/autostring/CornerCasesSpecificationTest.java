package io.github.yurloc.autostring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Specifies behavior of {@link AutoStringBuilder} in corner cases.
 */
public class CornerCasesSpecificationTest {

    /**
     * Field with null value should be converted to {@code "null"} string.
     */
    @Test
    public void nullValueDisplayedAsNull() {
        assertThat(new HasFieldWithNullValue().toString()).isEqualTo("HasFieldWithNullValue[nullField=null]");
    }

    /**
     * Passing null to AutoStringBuilder is wrong.
     */
    @Test(expected = IllegalArgumentException.class)
    public void nullArgumentThrowsException() {
        new PassesNullArgument().toString();
    }

    @Test(expected = TestException.class)
    public void shouldNotSwallowRuntimeExceptions() {
        new ThrowsRuntimeException().toString();
    }

    @Test(expected = TestError.class)
    public void shouldNotSwallowErrors() {
        new ThrowsError().toString();
    }

    /**
     * Not intended but should not fail.
     */
    @Test
    public void emptyBracketsForClassWithNoAutoStringFields() {
        assertThat(new HasNoAutoStringFields().toString()).isEqualTo("HasNoAutoStringFields[]");
    }

    /**
     * Not intended but should not fail.
     */
    @Test
    public void emptyBracketsForClassWithNoFields() {
        assertThat(new HasNoFields().toString()).isEqualTo("HasNoFields[]");
    }

    /**
     * Not intended but should not fail.
     */
    @Test
    public void shouldNotFailWhenArgumentIsString() {
        // TODO decide if String value should be displayed
        assertThat(new PassesStringInsteadOfThis().toString()).isEqualTo("String[]");
    }

    /**
     * Not intended but should not fail.
     */
    @Test
    public void shouldNotFailWhenArgumentIsPrimitiveType() {
        // TODO decide if primitive value should be displayed
        assertThat(new PassesPrimitiveBooleanInsteadOfThis().toString()).isEqualTo("Boolean[]");
    }

    private static class HasFieldWithNullValue {

        @AutoString
        private final String nullField = null;

        @Override
        public String toString() {
            return AutoStringBuilder.toString(this);
        }
    }

    private static class PassesNullArgument {

        @Override
        public String toString() {
            return AutoStringBuilder.toString(null);
        }
    }

    private static class TestException extends RuntimeException {

        private static final long serialVersionUID = 1L;

    }

    private static class ThrowsRuntimeException {

        @AutoString
        private Object field = new Object() {

            @Override
            public String toString() {
                throw new TestException();
            }
        };

        @Override
        public String toString() {
            return AutoStringBuilder.toString(this);
        }
    }

    private static class TestError extends Error {

        private static final long serialVersionUID = 1L;

    }

    private static class ThrowsError {

        @AutoString
        private Object field = new Object() {

            @Override
            public String toString() {
                throw new TestError();
            }
        };

        @Override
        public String toString() {
            return AutoStringBuilder.toString(this);
        }
    }

    private static class HasNoAutoStringFields {

        private final String value = "/";

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return AutoStringBuilder.toString(this);
        }
    }

    private static class HasNoFields {

        public void butSomeMethod() {
            // that does nothing
        }

        @Override
        public String toString() {
            return AutoStringBuilder.toString(this);
        }
    }

    private static class PassesStringInsteadOfThis {

        @Override
        public String toString() {
            return AutoStringBuilder.toString("hello");
        }
    }

    private static class PassesPrimitiveBooleanInsteadOfThis {

        @Override
        public String toString() {
            return AutoStringBuilder.toString(false);
        }
    }
}
