package com.vitily.cluster.gateway.config;

import com.vitily.cache.service.CommonServiceCache;
import com.vitily.cluster.gateway.foreign.UserInfo;
import com.vitily.common.consts.CommonEnumContainer;
import com.vitily.common.consts.DictionaryKey;
import com.vitily.common.module.Result;
import com.vitily.common.util.CommonUtil;
import com.vitily.common.util.JSONUtil;
import com.vitily.common.util.ServiceEncryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorityReactiveAuthorizationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler;
import org.springframework.security.web.server.authorization.DelegatingReactiveAuthorizationManager;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcherEntry;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.*;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Slf4j
public class AuthenticationConfiguration {
    @Value("${member.token.secret:123456}")
    private String memberTokenSecret;
    @Autowired
    CommonServiceCache commonServiceCache;
    private final static String BEARER = "Bearer ";

    /**
     * 构造匿名用户
     * @return
     */
    private AnonymousAuthenticationToken getAnonymousToken(){
        return new AnonymousAuthenticationToken("key", "anonymous", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
    }

    /**
     * 从http头中的AUTHORIZATION字段取数据，并且匹配。
     * @param bearer
     * @return
     */
    private AbstractAuthenticationToken getAuthentication(String bearer){
        if (StringUtils.isEmpty(bearer) || !bearer.startsWith(BEARER) || bearer.length() <= BEARER.length()){
            return getAnonymousToken();
        }
        final String[] _split = bearer.substring(BEARER.length()).split("\\|");
        if(_split.length != 3){
            return null;
        }
        final String authToken = _split[0];
        final String username = _split[1];
        final Long expires = Long.valueOf(_split[2]);
        if(expires <= System.currentTimeMillis()){
            log.warn("token 已过期");
            return null;
        }
        //验证token 的合法性（username+secretKey+expire）
        if(!CommonUtil.isEqual(authToken, ServiceEncryUtil.getMemberToken(username,memberTokenSecret+expires))){
            //throw new CustomerException("请不要修改token", CommonEnumContainer.ResultStatus.Token无效);
            log.warn("请不要修改token");
            return null;
        }
        CommonServiceCache memCache=commonServiceCache.getInstance(DictionaryKey.Keys.会员Token);
        if (!memCache.existsInServer(authToken)) {
            return null;
        }
        UserInfo userInfo = JSONUtil.parseObject((String)memCache.getFromServer(authToken),UserInfo.class);
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Integer role : userInfo.getRoles()){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role);
            grantedAuthorities.add(grantedAuthority);
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userInfo.getUserName(),userInfo,grantedAuthorities);
        return authentication;
    }

    /**
     * 从请求中取到http头 权限字段，并查看是否合法
     * @return
     */
    @Bean
    ServerAuthenticationConverter serverAuthenticationConverter() {
        return exchange -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            return Mono.justOrEmpty(getAuthentication(token));
        };
    }
    @Bean
    ReactiveAuthenticationManager reactiveAuthenticationManager() {
        return new ReactiveAuthenticationManager(){
            @Override
            public Mono<Authentication> authenticate(Authentication authentication) {
                log.info("authentication:{}",authentication.getPrincipal());
                return Mono.just(authentication);
            }
        };
    }
    @Bean
    AuthenticationWebFilter authenticationWebFilter(){
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(reactiveAuthenticationManager());
        authenticationWebFilter.setServerAuthenticationConverter(serverAuthenticationConverter());
        authenticationWebFilter.setSecurityContextRepository(serverSecurityContextRepository());
        authenticationWebFilter.setAuthenticationFailureHandler(new ServerAuthenticationEntryPointFailureHandler(serverAuthenticationEntryPoint()));
        return authenticationWebFilter;
    }

    /**
     *
     * @return
     */
    @Bean
    ServerSecurityContextRepository serverSecurityContextRepository(){
        return new ServerSecurityContextRepository(){
            @Override
            public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
                log.info("authentication:{}",context.getAuthentication());
                return Mono.empty();
            }

            @Override
            public Mono<SecurityContext> load(ServerWebExchange exchange) {
                log.info("exchange:{}",exchange);
                ServerHttpRequest request = exchange.getRequest();
                String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (StringUtils.isEmpty(token)) {
                    return Mono.empty();
                }
                //是否只保证一个token有效
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(getAuthentication(token));
                return Mono.just(securityContext);
            }
        };
    }

    /**
     * url权限判断
     * @return
     */
    @Bean
    WebFilter accessWebFilter() {
        return (exchange, chain) -> {
            DelegatingReactiveAuthorizationManager.Builder builder = DelegatingReactiveAuthorizationManager.builder();


            //将 builder对象放到内存中也许更加好。不需要每次都加载

            //匿名用户
            AuthorityReactiveAuthorizationManager anonymousManager = AuthorityReactiveAuthorizationManager.hasRole("ANONYMOUS");
            builder.add(new ServerWebExchangeMatcherEntry<>(ServerWebExchangeMatchers.pathMatchers("/auth/**"), anonymousManager));
            //从缓存中加载角色拥有的资源
            CommonServiceCache groupHasResources = commonServiceCache.getInstance(DictionaryKey.Keys.会员分组拥有的资源);
            Map<String,String> resources = groupHasResources.hgetAll(DictionaryKey.Keys.会员分组拥有的资源.getValue());
            if(resources != null){
                for(Map.Entry<String,String> entry:resources.entrySet()){
                    String patterns = entry.getValue();
                    if(StringUtils.isEmpty(patterns)){
                        continue;
                    }
                    AuthorityReactiveAuthorizationManager authorityManager0 = AuthorityReactiveAuthorizationManager.hasRole(entry.getKey());
                    builder.add(new ServerWebExchangeMatcherEntry<>(ServerWebExchangeMatchers.pathMatchers(StringUtils.toStringArray(JSONUtil.parseObject(patterns, List.class))), authorityManager0));
                }
            }
            DelegatingReactiveAuthorizationManager manager = builder.build();


            //在系统中所有角色对应的资源找到匹配该路径的资源，并查看该用户的角色是否匹配。
            return ReactiveSecurityContextHolder.getContext()
                    .filter(c -> c.getAuthentication() != null)
                    .map(SecurityContext::getAuthentication)
                    .as(authentication -> manager.verify(authentication, exchange))
                    .switchIfEmpty(chain.filter(exchange));
        };
    }

    /**
     * 允许跨域cors
     * @return
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public WebFilter corsWebFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                ServerHttpResponse response = exchange.getResponse();
                HttpHeaders headers = response.getHeaders();
                headers.add("Access-Control-Allow-Origin", "*");
                headers.add("Access-Control-Allow-Methods", "*");
                headers.add("Access-Control-Max-Age", "3600");
                headers.add("Access-Control-Allow-Headers","access-control-allow-origin");
                headers.add("Access-Control-Allow-Headers", HttpHeaders.AUTHORIZATION);
                headers.add("Access-Control-Allow-Headers", "Content-Type");
                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(exchange);
        };
    }

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http)throws Exception{
        return http

                .httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(serverAuthenticationEntryPoint())//处理认证异常
                .and()
                .addFilterAt(authenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)//认证
                .addFilterAt(accessWebFilter(), SecurityWebFiltersOrder.AUTHORIZATION)//动态鉴权
                .authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .build();
    }

    /**
     * 未登录、无权限返回错误信息
     * @return
     */
    @Bean
    ServerAccessDeniedHandler accessDeniedHandler(){
        return (exchange, denied) -> {
            return Mono.defer(() -> Mono.just(exchange.getResponse()))
                    .flatMap(response -> {
                        response.setStatusCode(HttpStatus.FORBIDDEN);
                        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
                        DataBufferFactory dataBufferFactory = response.bufferFactory();
                        DataBuffer buffer = dataBufferFactory.wrap(JSONUtil.toJSONString(Result.error(CommonEnumContainer.ResultStatus.无权限)).getBytes(
                                Charset.defaultCharset()));
                        return response.writeWith(Mono.just(buffer))
                                .doOnError( error -> DataBufferUtils.release(buffer));
                    });
        };
    }

    /**
     * token无效、匿名访问时返回错误信息
     * @return
     */
    @Bean
    ServerAuthenticationEntryPoint serverAuthenticationEntryPoint(){
        return (exchange, e) -> Mono.defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> {
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
                    DataBufferFactory dataBufferFactory = response.bufferFactory();
                    DataBuffer buffer = dataBufferFactory.wrap(JSONUtil.toJSONString(Result.error(CommonEnumContainer.ResultStatus.Token无效)).getBytes(
                            Charset.defaultCharset()));
                    return response.writeWith(Mono.just(buffer))
                            .doOnError( error -> DataBufferUtils.release(buffer));
                });
    }


}
