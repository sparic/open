package cn.muye.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ray.Fu on 2017/5/3.
 */
@Component
@ConfigurationProperties(prefix="devCenter.push")
public class CustomProperties {

    private String dirs;

    private String http;

    private List<String> excludeFileTypes;

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

    public List<String> getExcludeFileTypes() {
        return excludeFileTypes;
    }

    public void setExcludeFileTypes(List<String> excludeFileTypes) {
        this.excludeFileTypes = excludeFileTypes;
    }
}
