package com.sg;

import com.sg.source.common.annotation.Mapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sg"},
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Mapper.class} )
        })
public class Application{

    public static void main(String[] args){
        new SpringApplicationBuilder(Application.class).properties(
                "spring.config.location=classpath:/conf/application.properties"
        ).run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");

            /** bean 조회 */
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            int cnt = 0;
            for (String beanName : beanNames) {
                //System.out.println(beanName);
                cnt++;
            }
            System.out.println("======== bean count : " + cnt + " ========");
        };
    }



    /** resource 설정 추가 */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*webjar 설정 추가*/
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
        /*static 파일 설정 추가*/
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }


    /*public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }*/
}
