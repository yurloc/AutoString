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

    private static class HasFieldWithNullValue {

        @AutoString
        private final String nullField = null;

        @Override
        public String toString() {
            return AutoStringBuilder.build(this);
        }
    }

    private static class PassesNullArgument {

        @Override
        public String toString() {
            return AutoStringBuilder.build(null);
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
            return AutoStringBuilder.build(this);
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
            return AutoStringBuilder.build(this);
        }
    }
}
