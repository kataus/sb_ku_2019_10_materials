package ru.itvitality.sbrf.cu.cp.factoryMethod;

public class ConfigurationDB implements Configuration {
    @Override
    public String params() {
        return "params from DB";
    }
}
