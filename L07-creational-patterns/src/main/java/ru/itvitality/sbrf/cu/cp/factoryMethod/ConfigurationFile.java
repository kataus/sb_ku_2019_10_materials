package ru.itvitality.sbrf.cu.cp.factoryMethod;

public class ConfigurationFile implements Configuration {
    @Override
    public String params() {
        return "params from file";
    }
}
