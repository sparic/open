package cn.muye;

//import cn.muye.cache.RedissonUtil;
//import cn.muye.cache.redisson.RedissonBean;
import cn.muye.config.CustomProperties;
import cn.muye.support.FastJsonHttpMessageConverter;
//import cn.muye.support.HTTPJwtAuthorizeInterceptor;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.*;

import javax.sql.DataSource;
import java.util.*;

@EnableWebMvc
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@MapperScan({"cn.muye.**.mapper"})
@EnableConfigurationProperties({CustomProperties.class})
public class Application extends WebMvcConfigurerAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    private CustomProperties customProperties;

//    @Value("${devCenter.redisMasterAddressPort}")
//    private String masterAddress;

//    @Bean
//    public RedissonBean redissonBean() {
//        RedissonBean redissonBean = new RedissonBean();
//        redissonBean.setMasterAddress(masterAddress);
//        return redissonBean;
//    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);

        //核心代码
//       1、先定义一个convert转换消息的对象
//        2、添加fastjson的配置信息，比如是否要格式化返回的json数据；
//        3、在convert中添加配置信息
//        4、将convert添加到converters

        //1、先定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastjson的配置信息，比如是否要格式化返回的json数据；
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                //是否需要格式化
                SerializerFeature.PrettyFormat
        );
        //附加：处理中文乱码（后期添加）
        List<MediaType> fastMedisTypes = new ArrayList<MediaType>();
        fastMedisTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMedisTypes);
        //3、在convert中添加配置信息
//        fastConverter.setFastJsonConfig(fastJsonConfig);
        //4、将convert添加到converters
        converters.add(fastConverter);
    }

//    @Bean
//    public RedissonUtil redissonUtil() {
//        return new RedissonUtil();
//    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props);
        //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis*//**//*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


   /* @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HTTPJwtAuthorizeInterceptor httpJwtAuthorizeInterceptor = new HTTPJwtAuthorizeInterceptor();
        httpJwtAuthorizeInterceptor.setCustomProperties(customProperties);
        httpJwtAuthorizeInterceptor.setRedissonUtil(redissonUtil());
        registry.addInterceptor(httpJwtAuthorizeInterceptor).addPathPatterns("*//**")
     .excludePathPatterns("*//**//*user/login*//**", "*//**//*api-docs*//**");
     super.addInterceptors(registry);
     }*/

    /**
     * Start
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        LOGGER.info("SpringBoot Start Success");
    }

}
