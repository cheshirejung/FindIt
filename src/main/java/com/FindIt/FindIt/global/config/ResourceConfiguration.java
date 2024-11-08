package com.FindIt.FindIt.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class ResourceConfiguration implements WebMvcConfigurer, ApplicationListener<ApplicationStartedEvent> {
    @Value("${file.upload.rootPath}")
    private String rootPath;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.debug("event : {}", event);
    }


    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///"+ rootPath)
                //.setCacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES)) // 접근 파일 캐싱 시간
        ;

        /*
        // 리눅스 경우 root에서 시작하는 폴더 경로 지정 할 경우
        .addResourceLocations("file:///usr/download/")

        // 리소스 템플릿 경로를 지정할 경우
        .addResourceLocations("classpath:/templates/", "classpath:/static/")

        // 윈도우에서 실행 시 다음과 같은 형태로 드라이브 문자 포함 경로 지정
        .addResourceLocations("file:///E:/webserver_storage/")
        */
    }
}