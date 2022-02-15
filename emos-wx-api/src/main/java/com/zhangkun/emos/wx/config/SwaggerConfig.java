package com.zhangkun.emos.wx.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.DocumentType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("“秒见”在线协同办公应用系统");
        ApiInfo info = builder.build();
        docket.apiInfo(info);

        ApiSelectorBuilder selectorBuilder = docket.select();
        selectorBuilder.paths(PathSelectors.any());
        selectorBuilder.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));
        docket = selectorBuilder.build();

        ApiKey apiKey = new ApiKey("token", "token", "header");
        List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(apiKey);
        docket.securitySchemes(apiKeyList);

        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] scopes = {scope};
        SecurityReference reference = new SecurityReference("token", scopes);
        List refList = new ArrayList();
        refList.add(reference);
        SecurityContext context = SecurityContext.builder().securityReferences(refList).build();
        List ctxList = new ArrayList();
        ctxList.add(context);
        docket.securityContexts(ctxList);

        return docket;
    }

}