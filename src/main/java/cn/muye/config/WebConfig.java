package cn.muye.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.Ssl;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by enva on 2017/1/10.
 */
@Configuration
public class WebConfig
{

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
//                Ssl ssl = new Ssl();
//                //Server.jks中包含服务器私钥和证书
////              ssl.setKeyStore("C:\\Users\\enva\\server.jks");
//                ssl.setKeyStore("/opt/cer/server.jks");
//                ssl.setKeyStorePassword("123456");
//                container.setSsl(ssl);
//                container.setPort(9443);
            }
        };
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainerFactory() {
        TomcatEmbeddedServletContainerFactory factory =
                new TomcatEmbeddedServletContainerFactory() {
                    @Override
                    protected void postProcessContext(Context context) {
                        //SecurityConstraint必须存在，可以通过其为不同的URL设置不同的重定向策略。
                        SecurityConstraint securityConstraint = new SecurityConstraint();
                        securityConstraint.setUserConstraint("CONFIDENTIAL");
                        SecurityCollection collection = new SecurityCollection();
                        collection.addPattern("/*");
                        securityConstraint.addCollection(collection);
                        context.addConstraint(securityConstraint);
                    }
                };
        factory.addAdditionalTomcatConnectors(createHttpConnector());
        return factory;
    }

    private Connector createHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setSecure(false);
        connector.setPort(8081);
        connector.setRedirectPort(9443);
        return connector;
    }
}