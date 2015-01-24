package io.github.yurloc.autostring;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AutoStringBuilder {

    public static final String ERROR = "?";

    public static String toString(Object this_) {
        if (this_ == null) {
            throw new IllegalArgumentException("Argument 'object' must not be null.");
        }
        Class<? extends Object> cls = this_.getClass();
        AutoStringConfig config = cls.getAnnotation(AutoStringConfig.class);
        List<String[]> fieldValues = new ArrayList<String[]>();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if ((config != null && config.selectionStrategy() == SelectionStrategy.ALL)
                    || field.getAnnotation(AutoString.class) != null) {
                String str = "null";
                Object val = null;
                try {
                    field.setAccessible(true);
                    val = field.get(this_);
                } catch (IllegalAccessException ex) {
                    // shouldn't happen
                    throw new RuntimeException("Error getting value of field '" + field.getName() + "'.", ex);
                }
                if (val != null) {
                    // may throw a runtime exception, don't touch it
                    str = val.toString();
                }

                fieldValues.add(new String[]{getFieldDisplayName(field), str});
            }
        }
        StringBuilder sb = new StringBuilder(getClassDisplayName(cls));
        sb.append("[");
        for (int i = 0; i < fieldValues.size(); i++) {
            String[] pair = fieldValues.get(i);
            if (i > 0) {
                sb.append(",");
            }
            sb.append(pair[0]).append("=").append(pair[1]);
        }
        sb.append("]");
        return sb.toString();
    }

    private static String getFieldDisplayName(Field field) {
        AutoString annotation = field.getAnnotation(AutoString.class);
        if (annotation == null || annotation.displayName().equals("__default")) {
            return field.getName();
        }
        return annotation.displayName();
    }

    private static String getClassDisplayName(Class<?> cls) {
        AutoStringConfig config = cls.getAnnotation(AutoStringConfig.class);
        if (config != null && !config.displayName().equals("__default")) {
            return config.displayName();
        } else {
            return cls.getSimpleName();
        }
    }
}
