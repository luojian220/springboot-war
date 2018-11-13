package com.xingniu.zoon.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ExceptionLog {

    public static String exception(Throwable t) {
        if (null == t)
            return "";
        ByteArrayOutputStream bs = null;
        PrintStream ps = null;
        try {
            bs = new ByteArrayOutputStream();
            ps = new PrintStream(bs);
            t.printStackTrace(ps);
        } finally {
            try {
                if (null != ps) {
                    ps.close();
                }
                if (null != bs) {
                    bs.close();
                }
            } catch (Exception e) {
                return "";
            }
        }
        return bs.toString();
    }

    public static String exception(String errMsg, Throwable t) {
        StringBuffer buffer = new StringBuffer();
        if (null == t)
            return "";
        ByteArrayOutputStream bs = null;
        PrintStream ps = null;
        try {
            bs = new ByteArrayOutputStream();
            ps = new PrintStream(bs);
            t.printStackTrace(ps);
        } finally {
            try {
                if (null != ps) {
                    ps.close();
                }
                if (null != bs) {
                    bs.close();
                }
            } catch (Exception e) {
                return "";
            }
        }
        buffer.append(errMsg).append("\r\n").append(bs.toString());
        return buffer.toString();
    }
}
