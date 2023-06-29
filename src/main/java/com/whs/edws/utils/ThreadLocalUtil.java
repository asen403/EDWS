package com.whs.edws.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 线程本地工具类
 *
 * @author zed
 * @date 2023/02/28
 */
public class ThreadLocalUtil {

    private static ThreadLocal<Map<Object, Object>> RESOURCES = ThreadLocal.withInitial(HashMap::new);

    // 子线程可继承的ThreadLocal
    public static void initInherit() {
        removeResources();
        RESOURCES = new InheritableThreadLocal<>();
        RESOURCES.set(new HashMap<>());
    }

    public static void withInitial() {
        removeResources();
        RESOURCES = ThreadLocal.withInitial(HashMap::new);
    }

    public static void removeResources() {
        if (Objects.nonNull(RESOURCES)) {
            RESOURCES.remove();
        }
    }

    public static Map<Object, Object> getResources() {
        return Objects.isNull(RESOURCES) ? null : RESOURCES.get();
    }

    public static void put(Object key, Object value) {
        Objects.requireNonNull(key, "key cannot be null");
        Objects.requireNonNull(getResources(), "RESOURCES have not been inited");
        getResources().put(key, value);
    }

    public static Object get(Object key) {
        return Objects.isNull(getResources()) ? null : getResources().get(key);
    }

    public static Object remove(Object key) {
        return Objects.isNull(getResources()) ? null : getResources().remove(key);
    }

}
