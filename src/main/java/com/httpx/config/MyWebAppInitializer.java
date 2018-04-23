package com.httpx.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;


public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /**
     *  spring mvc 配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebConfig.class};
    }

    /**
     *  拦截请求
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     *  添加编码过滤器
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[]{characterEncodingFilter};
    }

    /**
     * 动态加载配置
     * @param dynamic
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic dynamic) {
        // 文件上传临时路径
        String filepath = "D://";
        // 设置单个最大上传文件 5Mb
        Long singleMax = (long)  (5 * Math.pow(2, 20));
        // 设置上传文件最大 10Mb
        Long totalMax = (long)(10*Math.pow(2,20));
        dynamic.setMultipartConfig(new MultipartConfigElement(filepath,singleMax,totalMax,0));
    }


}
