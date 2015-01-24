package io.github.yurloc.autostring;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoStringBuilder {

    public static final String ERROR = "?";

    public static String build(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Argument 'object' must not be null.");
        }
        Class<? extends Object> cls = object.getClass();
        List<String[]> fieldValues = new ArrayList<String[]>();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(AutoString.class) != null) {
                String str = "null";
                Object val = null;
                try {
                    field.setAccessible(true);
                    val = field.get(object);
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
        StringBuilder sb = new StringBuilder(cls.getSimpleName());
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
}
