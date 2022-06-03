package com.wjh.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

/**
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
public final class CommonUtils {

    private CommonUtils() {}

    /**
     * 获取UUID
     *
     * @return
     */
    public static synchronized String getMeUUID() {
        String strs = UUID.randomUUID().toString().replace("-", "");
        int intRandom = Math.abs(UUID.randomUUID().hashCode());
        String strtmp = "0a1b2c3d4e5f6a7b8c9d" + intRandom + "9f8e7d6c5b4a3f2e1d0c" + intRandom;// 初始化种子
        strs = RandomStringUtils.random(4, strtmp) + strs;// 返回intLen为的字符串
        return strs.toLowerCase();
    }

    /**
     * list根据对象某个属性去重
     *
     * @param keyExtractor
     * @return Predicate
     * @date 2021-07-28
     * @author wenjianhai
     * @since JDK 11
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 根据map的key排序
     *
     * @param map 待排序的map
     * @param isDesc 是否降序，true：降序，false：升序
     * @return 排序好的map
     * @author zero 2019/04/08
     */
    public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map, boolean isDesc) {
        Map<K, V> result = Maps.newLinkedHashMap();
        if (isDesc) {
            // 降序
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey().reversed())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            // 升序
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }

    /**
     * 替换消息模板中的变量
     *
     * @date 2022-03-11
     * @param dataMap     数据
     * @param tempContent 消息模板内容
     * @return String
     * @since JDK 1.8
     * @author wenjianhai
     */
    public static String replaceMsgTempParams(Map<String, Object> dataMap, String tempContent) {
        if (MapUtils.isEmpty(dataMap) || StringUtils.isBlank(tempContent)) {
            return null;
        }
        Iterator<Map.Entry<String, Object>> it = dataMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String strkey = entry.getKey();
            String strvalue = "";
            if (dataMap.containsKey(entry.getKey())) {
                if (entry.getValue() != null) {
                    strvalue = String.valueOf(entry.getValue());  //-----注意
                    strvalue = strvalue.trim();
                }

                String strs2 = "{" + strkey + "}";
                if (tempContent.indexOf(strs2) > -1) {
                    tempContent = tempContent.replace(strs2, strvalue);
                } else {
                    it.remove();
                }
            }
        }
        return tempContent;
    }

    /**
     * 字符串转List
     *
     * @param str       字符串
     * @param delimiter 分隔符
     * @return List
     * @date 2021-05-21
     * @author wenjianhai
     * @since JDK 11
     */
    public static List<String> strToList(String str, String delimiter) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        if (StringUtils.isBlank(delimiter)) {
            // 可能会对list进行add、remove操作，所以避免直接使用 Arrays.asList
            return new ArrayList<>(Arrays.asList(str));
        }
        List<String> list = Arrays.stream(str.split(delimiter)).map(s -> (s.trim())).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        // 过滤空字符串
        return list.stream().filter(s -> StringUtils.isNotBlank(s)).collect(Collectors.toList());
    }

    /**
     * List转字符串
     *
     * @date 2021-05-21
     * @param list
     * @param delimiter 分隔符
     * @return List
     * @since JDK 11
     * @author wenjianhai
     */
    public static String listToStr(List<String> list, String delimiter) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        // 过滤重复数据
        // list = list.stream().distinct().collect(Collectors.toList());
        // 过滤空字符串
        list = list.stream().filter(s -> StringUtils.isNotBlank(s)).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.stream().filter(s -> StringUtils.isNotBlank(s)).map(s -> s.trim()).collect(Collectors.joining(delimiter));
    }

    /**
     * 元转换成分
     *
     * @date 2022-03-23
     * @param yuan 元
     * @return int
     * @since JDK 1.8
     * @author wenjianhai
     */
    public static int yuanToFen(BigDecimal yuan) {
        if (yuan == null || yuan.compareTo(BigDecimal.ZERO) <= 0) {
            return 0;
        }
        // 分
        BigDecimal fen = yuan.multiply(Constants.PERCENT).setScale(Constants.AMOUNT_SCALE, BigDecimal.ROUND_HALF_DOWN);

        return fen.intValue();
    }
}
