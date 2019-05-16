package com.zero.zk.usage.config;

import java.util.Map;

public class ConfigManager {
    private transient Map<String, String> configMap = null;
    public ConfigManager(String nodePath) {
        ConfigClient configClient = new ConfigClient();
        configClient.init(nodePath, this);
    }
    public Map<String, String> getConfigMap() { return configMap; }
    public void setConfigMap(Map<String, String> configMap) {
        this.configMap = configMap;
    }
}
