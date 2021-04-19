package com.qdqtrj.lianlian.accp.util.accp;


import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 验证工具类
 *
 * @author accp
 */
public final class ValidatorUtils {
    private static final Validator VALIDATOR = Validation.byProvider(HibernateValidator.class).configure()
            //             快速失败模式
            //            .failFast(true)
            .buildValidatorFactory().getValidator();

    private ValidatorUtils() {
    }

    public static <T> Map<String, StringBuffer> validate(T obj) {
        Map<String, StringBuffer> errorMap = null;
        Set<ConstraintViolation<T>> set = VALIDATOR.validate(obj, Default.class);
        if (set != null && set.size() > 0) {
            errorMap = new HashMap<String, StringBuffer>(16);
            String property = null;
            for (ConstraintViolation<T> cv : set) {
                //这里循环获取错误信息，可以自定义格式
                property = cv.getPropertyPath().toString();
                if (errorMap.get(property) != null) {
                    errorMap.get(property).append("," + cv.getMessage());
                } else {
                    StringBuffer sb = new StringBuffer();
                    sb.append(cv.getMessage());
                    errorMap.put(property, sb);
                }
            }
        }
        return errorMap;
    }


}