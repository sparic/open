package cn.muye.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Ray.Fu on 2017/5/3.
 */
@Component
@ConfigurationProperties(prefix="devCenter.push")
public class CustomProperties {

    private String dirs;

    private String http;

    public String getDirs() {
        return dirs;
    }

    public void setDirs(String dirs) {
        this.dirs = dirs;
    }

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }
}
