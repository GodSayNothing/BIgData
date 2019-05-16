package com.zero.zk.usage.config;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static Map<String, String> parseConfig(String configStr) {
        String[] pairs = configStr.split("&");
        Map<String, String> map = new HashMap<>();
        for (String line: pairs) {
            String[] kv = line.split("=");
            map.put(kv[0], kv[1]);
        }
        return map;
    }
}
