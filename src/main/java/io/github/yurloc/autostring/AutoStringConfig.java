package io.github.yurloc.autostring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// TODO maybe toString() should be "recommended" target (not to pollute class declaration), or allow both?
@Target(ElementType.TYPE)
@Inherited
public @interface AutoStringConfig {

    String displayName() default "__default";

    SelectionStrategy selectionStrategy() default SelectionStrategy.ANNOTATED;
}
