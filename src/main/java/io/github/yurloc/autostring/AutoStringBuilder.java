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
        List<String[]> fieldValues = new ArrayList<String[]>();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(AutoString.class) != null) {
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

                fieldValues.add(new String[]{field.getName(), str});
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

    private static String getClassDisplayName(Class<?> cls) {
        AutoStringConfig config = cls.getAnnotation(AutoStringConfig.class);
        if (config != null) {
            return config.displayName();
        } else {
            return cls.getSimpleName();
        }
    }
}
