package io.github.yurloc.autostring;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.yurloc.autostring.accessibility.Child;
import org.junit.Test;

public class AutoStringConfigInheritanceTest {

    @Test
    public void testConfigInheritance() {
        assertThat(new Child().toString()).isEqualTo("Child[childField=childField]");
    }
}
