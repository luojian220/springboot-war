package com.xingniu.zoon.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * @author tony
 * @version 1.0
 * //TODO: 2018/6/25 汉字转拼音工具类
 */
public class Pinyin4jUtil {

    private static HanyuPinyinOutputFormat PINYIN_FORMAT;

    static {
        PINYIN_FORMAT = new HanyuPinyinOutputFormat();
        PINYIN_FORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        PINYIN_FORMAT.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    public static String toPinyin(String input) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c <= 255) {
                sb.append(c);
            } else {
                String pinyin = null;
                try {
                    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, PINYIN_FORMAT);
                    pinyin = pinyinArray[0];
                } catch (Exception e) {
                }
                if (pinyin != null) {
                    sb.append(pinyin);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        StringBuilder pinyin = new StringBuilder();
        String str = "阿里巴巴1234abc";
        String result = toPinyin(str);
        System.out.println(result.toUpperCase());
    }
}
