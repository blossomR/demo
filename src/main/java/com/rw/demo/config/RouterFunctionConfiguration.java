package com.rw.demo.config;

import com.rw.demo.domain.User;
import com.rw.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * 路由器函数  配置
 */
@Configuration//@Configuration 相当于java版xml文件的作用
public class RouterFunctionConfiguration {

    /**
     * Servlet
     * 请求接口：ServletRequest 或者 HttpServletRequest
     * 请求接口：ServletResponse或者 HttpServletResponse
     * Spring 5.0 重新定义了服务请求和响应接口
     * 请求接口：ServletRequest
     * 请求接口：ServletResponse
     * 既可以支持Servlet规范，也可以支持自定义 ，比如netty（web Server）
     * example:
     * 定义GET请求，并返回所有用户对象 URI： /person/find/all
     * Flux是0-N个对象集合
     * Mono是0-1个对象集合
     * Reactive中的Flux或者Mono是异步处理（非阻塞）
     * 集合方法基本上是同步处理(阻塞)
     * Flux或者Mono都是Publisher
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> personFindAll(UserRepository userRepository) {
        Collection<User> users = userRepository.findAll();
        return RouterFunctions.route(RequestPredicates.GET("/person/find/all"),
                request -> {
                    //返回所有用户对象
                    Mono<ServerResponse> response = null;
                    Flux<User> userFlux = Flux.fromIterable(users);
                    return ServerResponse.ok().body(userFlux, User.class);
                });
    }
}
