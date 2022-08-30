package com.zhw.helloworld.xssFilter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * XSS过滤处理
 *
 * @author eims
 */
@Slf4j
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest httpServletRequest = null;


    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        httpServletRequest = request;

    }

    public ServletInputStream getInputStream() throws IOException {

        if (httpServletRequest.getContentType().contains(MediaType.MULTIPART_FORM_DATA.getType())) {
            return super.getInputStream();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()));
        String line;
        StringBuilder result = new StringBuilder();

        while ((line = br.readLine()) != null) {
            log.debug("xss getInputStream line {}", line);
            if (result.length() == 0) {

                result.append(clean(line));
            } else {
                result.append("\r\n").append(clean(line));
            }

            log.debug("xss getInputStream result {}", result);
        }

        br.close();

        return new WrappedServletInputStream(new ByteArrayInputStream(result.toString().getBytes("UTF-8")));
    }

    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤。
     * <p>
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取
     * <p>
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(String name) {
        name = clean(name);
        String value = super.getParameter(name);
        if (StringUtils.isNotBlank(value)) {
            value = clean(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] arr = super.getParameterValues(name);
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = clean(arr[i]);
            }
        }
        return arr;
    }


    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。
     * <p>
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取
     * <p>
     * getHeaderNames 也可能需要覆盖
     */
    @Override
    public String getHeader(String name) {

        name = clean(name);
        String value = super.getHeader(name);
        if (StringUtils.isNotBlank(value)) {
            value = clean(value);
        }
        return value;
    }

    /**
     * 获取最原始的request
     *
     * @return
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * 获取最原始的request的静态方法
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) req).getHttpServletRequest();
        }

        return req;
    }

    public String clean(String content) {
        String result = xssEncode(content);
        return result;
    }

    private class WrappedServletInputStream extends ServletInputStream {
        public void setStream(InputStream stream) {
            this.stream = stream;
        }

        private InputStream stream;

        public WrappedServletInputStream(InputStream stream) {
            this.stream = stream;
        }

        @Override
        public int read() throws IOException {
            return stream.read();
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }
    }

    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     *
     * @param s
     * @return
     */
    private static String xssEncode(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    sb.append('＞');//全角大于号
                    break;
                case '<':
                    sb.append('＜');//全角小于号
                    break;
                case '\'':
                    sb.append('‘');//全角单引号
                    break;
                case '&':
                    sb.append('＆');//全角
                    break;
//                case '\\':
//                    sb.append('＼');//全角斜线
//                    break;
                case '%':
                    sb.append('％');//置空
                    break;
//                case '+':
//                    sb.append('＋');//置空
//                    break;
                case '(':
                    sb.append('（');//置空
                    break;
                case ')':
                    sb.append('）');//置空
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

}