package ru.itvitality.sbrf.cu.cp.factoryMethod;

public class ConfigurationFactory {
    public static Configuration getConfiguration(String param) {
        if ("file".equals(param)) {
            return new ConfigurationFile();
        }
        if ("db".equals(param)) {
            return new ConfigurationDB();
        }
        throw new IllegalArgumentException("unknown param:" + param);
    }
}
