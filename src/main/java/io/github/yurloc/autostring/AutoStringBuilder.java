package io.github.yurloc.autostring;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoStringBuilder {

    public static final String ERROR = "?";

    public static String build(Object o) {
        if (o == null) {
            return "null";
        }
        Class<? extends Object> cls = o.getClass();
        List<String[]> fieldValues = new ArrayList<String[]>();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(AutoString.class) != null) {
                String str = "null";
                Object val = null;
                try {
                    field.setAccessible(true);
                    val = field.get(o);
                } catch (IllegalArgumentException ex) {
                    // if 'o' is not instance of field's class - can't happen
                    Logger.getLogger(AutoStringBuilder.class.getName()).log(Level.SEVERE, null, ex);
                    str = ERROR;
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(AutoStringBuilder.class.getName()).log(Level.SEVERE, null, ex);
                    str = ERROR;
                }
                if (val != null) {
                    try {
                        str = val.toString();
                    } catch (Throwable t) {
                        Logger.getLogger(AutoStringBuilder.class.getName()).log(Level.SEVERE, null, t);
                        str = ERROR;
                    }
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
