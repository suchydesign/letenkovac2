package cheapFlights.models;

import java.lang.reflect.Field;

public final class PostTypes {
    public static final int undefined = 0;
    public static final int status = 1;
    public static final int link = 2;
    public static final int photo = 3;
    public static final int video = 4;
    public static final int offer = 5;

    @SuppressWarnings("unchecked")
    public static <V> V get(String fieldName) {
        Class<?> clazz = PostTypes.class;
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName.toLowerCase());
                field.setAccessible(true);
                return (V) field.get(clazz);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return null;
    }
}
