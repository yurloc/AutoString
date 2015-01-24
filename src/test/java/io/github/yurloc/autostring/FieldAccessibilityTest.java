package io.github.yurloc.autostring;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import io.github.yurloc.autostring.accessibility.Accessibility;
import org.junit.Before;
import org.junit.Test;

public class FieldAccessibilityTest {

    private CharSequence[] pairs;

    @Before
    public void setUp() {
        List<String> list = new ArrayList<>();
        String[] modifiers = new String[]{"no modifier", "static", "final", "volatile", "transient"};
        String[] accessLevels = new String[]{"Pvt", "Pkg", "Ptd", "Pub"};
        for (String modifier : modifiers) {
            for (String access : accessLevels) {
                String fieldName = modifier.charAt(0) + access;
                list.add(fieldName + "=" + fieldName + ";");
            }
        }
        pairs = new CharSequence[list.size()];
        pairs = list.toArray(pairs);
        assertThat(pairs).hasSize(modifiers.length * accessLevels.length);
    }

    @Test
    public void testFieldAccessModifiers() {
        Accessibility dummy = new Accessibility();
        assertThat(dummy.toString())
                .contains(pairs)
                .doesNotContain("?");
        System.out.println(dummy);
    }
}
