package com.qdqtrj.lianlian.accp.api.notify;

import com.qdqtrj.lianlian.accp.config.AccpConstants;
import com.qdqtrj.lianlian.accp.config.AccpPayConfig;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 异步通知处理工具类
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
//@UtilityClass // == static and private construct
public final class AccpNotifyHelper {

    private AccpNotifyHelper() {
    }

    /**
     * 用抽象类和范型解决重复代码，你只需要new AbsNotifyCallback在businessCode方法中实现业务逻辑代码即可
     * 成功返回true，失败返回false或抛出Exception子类异常即可
     *
     * @param accpPayConfig accp支付配置对象
     * @param request       HttpServletRequest 可直接无缝接入springmvc、普通javaweb项目
     * @param notifyClass   异步消息json字符对应异步通知类 类类型
     * @param callback      new AccpNotifyCallback 实现你的业务逻辑代码
     * @param <T>
     * @return Success：标识异步接口处理成功，空字符：标识失败
     * @throws IOException 无法从请求对象中获取异步消息，报错
     */
    public static <T> String doNotify(AccpPayConfig accpPayConfig, HttpServletRequest request,
                                      Class<T> notifyClass, AbstractAccpNotifyCallback<T> callback) {
        try {
            return callback.pretreatment(accpPayConfig, request, notifyClass);
        } catch (Exception e) {
            // 回调处理失败
            return AccpConstants.FAILED;
        }
    }

    public static <T> String doNotify(AccpPayConfig accpPayConfig, String signatureData, String body,
                                      Class<T> notifyClass, AbstractAccpNotifyCallback<T> callback) {
        try {
            return callback.pretreatment(accpPayConfig, signatureData, body, notifyClass);
        } catch (Exception e) {
            // 回调处理失败
            return AccpConstants.FAILED;
        }
    }
}
