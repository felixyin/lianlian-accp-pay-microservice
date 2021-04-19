package com.qdqtrj.lianlian.accp.pojo;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

/**
 * 生成风控参数工具类，测试用，切换生产只需要业务传递过来即可覆盖
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/23 3:19 下午
 */
@Slf4j
public class GenRiskItemHelper {

    private static final String[] TEL_FIRST = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    private GenRiskItemHelper() {
    }

    /**
     * 生成商品名称
     */
    public static String genGoodName() {
        Random random = new Random(System.currentTimeMillis());
        /* 598 百家姓 */
        String[] surname = {"森麒麟", "路航", "德林特", "吉翔速", "飞机轮胎", "商用轮胎"};

        int index = random.nextInt(surname.length - 1);
        //获得一个随机的姓氏
        String name = surname[index];

        /* 从常用字中选取一个或两个字作为名 */
        if (random.nextBoolean()) {
            name += getChinese() + getChinese();
        } else {
            name += getChinese();
        }
        return name;
    }

    public static String getChinese() {
        String str = null;
        int highPos, lowPos;
        Random random = new Random();
        //区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字
        highPos = (176 + Math.abs(random.nextInt(71)));
        random = new Random();
        //位码，0xA0打头，范围第1~94列
        lowPos = 161 + Math.abs(random.nextInt(94));

        byte[] bArr = new byte[2];
        bArr[0] = (Integer.valueOf(highPos)).byteValue();
        bArr[1] = (Integer.valueOf(lowPos)).byteValue();
        try {
            //区位码组合成汉字
            str = new String(bArr, "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error("不支持的编码格式：", e);
        }
        return str;
    }

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    /**
     * 生成手机号码
     */
    public static String genPhone() {
        int index = getNum(0, TEL_FIRST.length - 1);
        String first = TEL_FIRST[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }

    /**
     * 生成时间
     */
    public static String genDatetime() {
        Calendar c = Calendar.getInstance();
        // 昨天
        c.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHMMSS");
        return sdf.format(c.getTime());
    }

    /**
     * 生成商品类目
     */
    public static String genCategory() {
        return "5002";
    }

    /**
     * 生成用户编号
     */
    public static String genUserCode() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成风控参数实例
     */
    public static RiskItem randomRiskItem() {
        return new RiskItem(genCategory(), genUserCode(), genPhone(), genDatetime(), genGoodName());
    }

}

