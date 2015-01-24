package io.github.yurloc.autostring;

public enum SelectionStrategy {

    /**
     * Only select fields annotated with {@link AutoString}. This is the default strategy.
     */
    ANNOTATED,
    /**
     * Select <a href="http://docs.oracle.com/javase/tutorial/javabeans/writing/properties.html">JavaBean
     * properties</a>.
     * When property getter is found it will be used to derive property name and get the value. When setter is found and
     * matching getter does not exist (write-only property), property name will be derived from setter and value will be
     * extracted from a field with that name. If no such field exists, {@link IllegalStateException} will be thrown
     * (TODO be less pedantic and just ignore the property and log a warning).
     */
    BEAN_PROPERTIES,
    /**
     * Select all fields.
     */
    ALL
}
