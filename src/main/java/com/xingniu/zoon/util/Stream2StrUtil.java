package com.xingniu.zoon.util;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;


/**
 * @author tony
 * @version 1.0
 * //TODO: 2018/7/18 inputstream转字符串的3种实现
 */
@Component
public class Stream2StrUtil {

    private final static Logger logger = LoggerFactory.getLogger(Stream2StrUtil.class);


    public String parse1(InputStream inputStream) {
        String str = null;
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8.name());
            str = writer.toString();
        } catch (IOException e) {
            logger.error(ExceptionLog.exception(e));
        } finally {
            try {

                if (null != writer) {
                    writer.flush();
                    writer.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {

            }
        }
        return str;
    }


    public String parse2(InputStream inputStream) {
        String str = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
            str = bos.toString(StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            logger.error(ExceptionLog.exception(e));
        } finally {
            try {

                if (null != bos) {
                    bos.flush();
                    bos.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {

            }
        }
        return str;

    }

    public String parse3(InputStream inputStream) {
        String str = null;
        BufferedReader br = null;
        try {
            StringBuffer sb = new StringBuffer();
            String line;

            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            str = sb.toString();
        } catch (Exception e) {
            logger.error(ExceptionLog.exception(e));
        } finally {
            try {
                if (null != br) {
                    br.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {

            }
        }

        return str;
    }

}
