package cn.im.tool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Sets;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger介绍
 * 优点：实时更新接口文档规范；可在线进行接口调试；规范化管理多份文档
 * 缺点：代码浸入行强
 * 
 * 使用
 * 1.添加依赖
 * <dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger2</artifactId>
	<version>2.6.1</version>
</dependency>

<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger-ui</artifactId>
	<version>2.6.1</version>
</dependency>
 * 
 * 2.添加Swagger2配置类 -- 当前类
 *  相当于xml配置文件
 * 
 * 3.Testful接口中，通过注解使用
 * @ApiOperation
 * @ApiImplicitParam
 *
 *4.访问http://127.0.0.1:8081/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.protocols(Sets.newHashSet("http")) //协议,http或http2
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("cn.im.controller"))//扫描指定路径
				.paths(PathSelectors.any())
				.build();
				
	}
	
	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("springboot利用swagger构建api文档")
				.version("V1.0")
				.description("用于测试RESTful API")
				.build();
	}
}
