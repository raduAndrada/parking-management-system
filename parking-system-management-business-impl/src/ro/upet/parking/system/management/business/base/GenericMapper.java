package ro.upet.parking.system.management.business.base;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import ro.upet.parking.system.management.data.api.base.BaseEntity;
import ro.upet.parking.system.management.model.base.annotations.MapperNonNull;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Map;
import java.util.Objects;

/**
 * Utility class for all mappers
 *
 * @param <E> the class of the entity for the mapper
 * @param <M> the class for the model of the mapper
 * @author Andrada
 */
@Slf4j
public class GenericMapper<E, M> {

    private static final String READ = "READ";
    private static final String WRITE = "WRITE";


    /**
     * @param model  for the entity
     * @param entity the entity
     * @param args   the additional classes inside in this entity
     */
    public void mapToEntity(final M model, final E entity, Object... args) {
        map(model, entity);
        try {
            entity.getClass().getDeclaredMethod("setBase", BaseEntity.class).invoke(entity, new BaseEntity());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException | NullPointerException e) {
            log.warn("Failed to map to object: {}", e.toString());
        }
        invokeMethodsOnChildClasses(entity, args);
    }

    public void mapToModel(final E entity, final M model, Object... args) {
        map(entity, model);
        try {
            Method getBase = entity.getClass().getDeclaredMethod("getBase");
            BaseEntity be = (BaseEntity) getBase.invoke(entity);
            model.getClass().getDeclaredMethod("setCode", String.class).invoke(model, be.getCode());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException| NullPointerException e) {
            log.warn("Failed to map to object: {}", e.toString());
        }
        invokeMethodsOnChildClasses(model, args);
    }

    private void invokeMethodsOnChildClasses(final Object destination, Object... args) {
        if (args != null && args.length > 2) {
            for (int i = 0; i < args.length - 2; i++) {
                final Object from = args[i];
                final Object to = args[++i];
                map(from, to);
                final Method setterMethod = (Method) args[++i];
                try {
                    setterMethod.invoke(destination, from);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    log.warn("Failed to map to inner object: {}", e.toString());
                }
            }
        }
    }

    private void map(final Object from, final Object to) {
        if (Objects.nonNull(from) && Objects.nonNull(to)) {
            final Map<String, Method> writeMethodMap = getClassMethodsMap(to.getClass(), WRITE);
            final Map<String, Method> readMethodMap = getClassMethodsMap(from.getClass(), READ);

            writeMethodMap.entrySet().stream().forEach(entry -> {
                final Method readMethod = readMethodMap.get(entry.getKey());
                final Method writeMethod = entry.getValue();
                if (Objects.nonNull(readMethod)) {
                    try {
                        Object readValue = readMethod.invoke(from);
                        if (Objects.nonNull(writeMethod.getAnnotation(MapperNonNull.class)) && Objects.isNull(readValue)) {
                            final TypeVariable[] types = writeMethod.getTypeParameters();
                            readValue = Class.forName(types[0].getClass().getName()).getConstructor().newInstance();
                        }
                        writeMethod.invoke(to, readValue);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                            | InstantiationException | NoSuchMethodException | SecurityException
                            | ClassNotFoundException e) {
                        log.warn("Failed to map to object: {}", e.toString());
                    }
                }
            });
            log.info(String.format("Mapped object is: %s", to.toString()));
        }
        else {
            log.warn("Objects could not be mapped due to nullity, from {} to {}", from, to);
        }
    }

    private Map<String, Method> getClassMethodsMap(final Class<?> clasz, final String methodType) {
        final Map<String, Method> classMethodsMap = Maps.newHashMap();

        PropertyDescriptor[] claszProperties = BeanUtils.getPropertyDescriptors(clasz);
        for (PropertyDescriptor pd : claszProperties) {

            final String propertyName = pd.getName();
            if (methodType.equals(WRITE)) {
                final Method m = pd.getWriteMethod();
                if (Objects.nonNull(m)) {
                    classMethodsMap.put(propertyName, m);
                }
            }
            if (methodType.equals(READ)) {
                final Method m = pd.getReadMethod();
                if (Objects.nonNull(m)) {
                    classMethodsMap.put(propertyName, m);
                }
            }
        }

        return classMethodsMap;

    }

}
