package com.lu.excel;

import com.google.common.base.Objects;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 * auto copy the same name field to target
 */
public class ObjectHelper {
//    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectHelper.class);
    private static final Multimap<ClassComparator, FieldMatcher> classCache = HashMultimap.create();

    /**
     * copy data
     *
     * @param target target class
     * @param data   data
     * @param <T>    target generic
     * @return
     */
    public static <T> T copy(Class<T> target, Object data) {
        T instance;

        try {
            Constructor<T> constructor = target.getDeclaredConstructor();
            constructor.setAccessible(true);
            instance = constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(target.getName() + "can not be instance!", e);
        }
        ClassComparator classComparator = new ClassComparator(target.getClass(), data.getClass());
        Field[] declaredFields = data.getClass().getDeclaredFields();
        Collection<FieldMatcher> matcherCollection = classCache.get(classComparator);
        if (matcherCollection.isEmpty()) {
            synchronized (classCache) {
                if (matcherCollection.isEmpty()) {
                    for (Field declaredField : declaredFields)
                        try {
                            Field field = instance.getClass().getDeclaredField(declaredField.getName());
                            if (field.getType().equals(declaredField.getType())) {
                                FieldMatcher fieldMatcher = new FieldMatcher(field, declaredField);
                                classCache.put(classComparator, fieldMatcher);
                            }
                        } catch (NoSuchFieldException e) {
//                            LOGGER.error("[{}] does not exist in [{}]", declaredField.getName(), target.getName());
                        }
                }
            }
        }
        for (FieldMatcher fieldMatcher : matcherCollection) {
            fieldMatcher.copy(instance, data);
        }
        return instance;
    }

    private static class ClassComparator {
        private Class target;
        private Class dataClass;

        public ClassComparator(Class target, Class dataClass) {
            this.target = target;
            this.dataClass = dataClass;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ClassComparator)) return false;
            ClassComparator that = (ClassComparator) o;
            return Objects.equal(target, that.target) && Objects.equal(dataClass, that.dataClass);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(target, dataClass);
        }
    }

    private static class FieldMatcher {
        Field targetField;
        Field dataField;

        public FieldMatcher(Field targetField, Field dataField) {
            this.targetField = targetField;
            this.dataField = dataField;
            dataField.setAccessible(true);
            targetField.setAccessible(true);
        }

        public void copy(Object target, Object data) {
            try {
                Object result = dataField.get(data);
                targetField.set(target, result);
            } catch (Exception e) {
//                LOGGER.error("FieldMatcher.copy [" + targetField.getName() + "]+ can not be resolve!", e);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FieldMatcher)) return false;
            FieldMatcher that = (FieldMatcher) o;
            return Objects.equal(targetField, that.targetField) &&
                    Objects.equal(dataField, that.dataField);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(targetField, dataField);
        }
    }
}
