package io.github.yurloc.autostring;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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
                .doesNotContain(AutoStringBuilder.ERROR);
        System.out.println(dummy);
    }

    /**
     * Demonstrates that field accessibility is not affected by calling
     * {@link AutoStringBuilder#build(java.lang.Object)}.
     * More info on <a href="http://stackoverflow.com/a/10638983/1691152">Stack Overflow</a>.
     *
     * @throws NoSuchFieldException shouldn't happen
     * @throws IllegalArgumentException shouldn't happen
     * @throws IllegalAccessException this one is expected
     */
    @Test(expected = IllegalAccessException.class)
    public void testAccesibilityAfterToString() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Accessibility dummy = new Accessibility();
        dummy.toString();
        Field nPvt = dummy.getClass().getDeclaredField("nPvt");
        assertThat(Modifier.isPrivate(nPvt.getModifiers()));
        nPvt.get(dummy);
    }
}
