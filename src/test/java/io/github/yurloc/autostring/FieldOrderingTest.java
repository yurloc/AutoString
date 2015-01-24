package io.github.yurloc.autostring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class FieldOrderingTest {

    /**
     * Reflection API does not define the order but it appears that order in which fields are declared is kept. Let's
     * have a test to confirm that.
     */
    @Test
    public void shouldKeepOrderOfDeclaration() {
        assertThat(new Dummy().toString()).isEqualTo("Dummy[zzz=zzz,min=-1,max=1,d=null]");
    }

    private static class Dummy {

        @AutoString
        private String zzz = "zzz";

        @AutoString
        private final int min, max;

        @AutoString
        private final Dummy d = null;

        public Dummy() {
            this.min = -1;
            this.max = 1;
        }

        @Override
        public String toString() {
            return AutoStringBuilder.toString(this);
        }
    }
}
