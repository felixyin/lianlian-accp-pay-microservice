package com.qdqtrj.lianlian.accp.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

/**
 * mongodb document 对应 javabean
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
@Data
@Document("accp_pay_record")
public class AccpPayRecordModel {

    @Id
    private ObjectId id;

    @Field("url")
    private String url;

    @Field("action")
    private String action;

    /**
     * // TODO add by yinbin, nginx需配置：X-Real-IP
     * upstreamwangguan{
     * server127.0.0.1:81;
     * }
     * <p>
     * server{
     * listen 80;  #端口号
     * server_name localhost; #客户端请求地址
     * location/{
     * proxy_passhttp://wangguan/; #转发地址 ，这里表示转发到网关upstream 那里
     * proxy_set_header Host $host;
     * proxy_set_header X-Real-IP$remote_addr;
     * proxy_set_header X-Forwarded-For  $proxy_add_x_forwarded_for;#配置请求头
     * }
     * }
     */
    @Field("sourceIp")
    private String sourceIp;

    @Field("req")
    private Object req;

    @Field("res")
    private Object res;

    @Field("notify")
    private Object notify;


    public AccpPayRecordModel(String url, String action, Object req, Object res, String sourceIp) {
        this.url = url;
        this.action = action;
        this.req = req;
        this.res = res;
        this.sourceIp = sourceIp;
    }

    public String getReqFormat() {
        if (null == this.req) {
            return "";
        }
        Map<String, Object> annoMap = getStringObjectMap(this.req);
        return JSON.toJSONString(annoMap, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
    }

    public String getResFormat() {
        if (null == this.res) {
            return "";
        }
        Map<String, Object> annoMap = getStringObjectMap(this.res);
        return JSON.toJSONString(annoMap, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
    }

    public String getNotifyFormat() {
        if (null == this.notify) {
            return "";
        }
        Map<String, Object> annoMap = getStringObjectMap(this.notify);
        return JSON.toJSONString(annoMap, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
    }

    @SuppressWarnings("rawtypes")
    private Map<String, Object> getStringObjectMap(Object req) {
        java.lang.reflect.Field[] declaredFields = req.getClass().getDeclaredFields();
        Map<String, Object> annoMap = Maps.newHashMap();
        for (java.lang.reflect.Field field : declaredFields) {
            try {
                ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                String key = annotation.value();
                field.setAccessible(true);
                Object value = field.get(req);
                if (StringUtils.isNotBlank(key)) {
                    String[] split = key.split(",");
                    try {
                        key = split[1];
                    } catch (Exception e) {
                        key = field.getName();
                    }
                }
                Class<?> aClass = value.getClass();
                String name = aClass.getName();
                boolean anonymousClass = !name.startsWith("java.lang") && !name.startsWith("java.util");
                if (anonymousClass) {
                    Map<String, Object> map2 = Maps.newHashMap();
                    setPropertiesNameAndValue(value, aClass, map2);
                    annoMap.put(key, map2);
                } else if (name.contains("java.util.ArrayList")) {
                    List<Map<String, Object>> nList = Lists.newArrayList();
                    List list = (List) value;
                    for (Object obj : list) {
                        Map<String, Object> map2 = Maps.newHashMap();
                        setPropertiesNameAndValue(obj, obj.getClass(), map2);
                        nList.add(map2);
                    }
                    annoMap.put(key, nList);
                } else {
                    annoMap.put(key, value);
                }
            } catch (NullPointerException | IllegalArgumentException | IllegalAccessException e) {
            }
        }
        return annoMap;
    }

    private void setPropertiesNameAndValue(Object value, Class<?> aClass, Map<String, Object> map2) throws IllegalAccessException {
        java.lang.reflect.Field[] declaredFields1 = aClass.getDeclaredFields();
        for (java.lang.reflect.Field field1 : declaredFields1) {
            ApiModelProperty annotation1 = field1.getAnnotation(ApiModelProperty.class);
            String key1 = annotation1.value();
            field1.setAccessible(true);
            Object value1 = field1.get(value);
            if (StringUtils.isNotBlank(key1)) {
                String[] split = key1.split(",");
                try {
                    key1 = split[1];
                } catch (Exception e) {
                    key1 = field1.getName();
                }
            }
            map2.put(key1, value1);
        }
    }
}
