package com.daruan.commons;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import java.util.Map;
import java.util.UUID;

/**
 * Created by ruanlinyu on 2016/12/28.
 */
public class CommonUtils {
    /**
     * 返回一个不重复的字符串
     * @return
     */
    public static String uuid()
    {
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }

    /**
     * 把map转换成对象
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBean(Map map,Class<T> clazz)
    {
        try {
			/*
			 * 1. 通过参数clazz创建实例
			 * 2. 使用BeanUtils.populate把map的数据封闭到bean中
			 */
            T bean = clazz.newInstance();
            ConvertUtils.register(new DateConverter(), java.util.Date.class);
            BeanUtils.populate(bean, map);
            return bean;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}
