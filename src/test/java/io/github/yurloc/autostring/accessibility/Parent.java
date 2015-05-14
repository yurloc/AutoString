package io.github.yurloc.autostring.accessibility;

import io.github.yurloc.autostring.AutoStringBuilder;
import io.github.yurloc.autostring.AutoStringConfig;
import io.github.yurloc.autostring.SelectionStrategy;

@AutoStringConfig(selectionStrategy = SelectionStrategy.ALL)
public class Parent {
    
    @Override
    public String toString() {
        return AutoStringBuilder.toString(this);
    }
}
