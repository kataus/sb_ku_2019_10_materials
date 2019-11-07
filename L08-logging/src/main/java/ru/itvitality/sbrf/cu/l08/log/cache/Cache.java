package ru.itvitality.sbrf.cu.l08.log.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sergey
 * created on 14.08.18.
 */
public class Cache {

    private final Map<String, Data> dataStore = new HashMap<>();

    public static void main(String[] args) {
        Cache cache = new Cache();
        cache.fillCache();
        cache.go();
    }

    private void go() {
        final Data d1 = dataStore.get("k1");
        DataProcessor.process(d1);
        final Data d12 = dataStore.get("k1");
        System.out.println("key:" + d12.getId() + " values:" + d12.getValues());
    }

    private void fillCache() {
        List<String> data = new ArrayList<>();
        for(int idx = 0; idx < 10; idx++) {
            data.add("v" + idx);
        }
        dataStore.put("k1", new Data(1, data));
        dataStore.put("k2", new Data(2, data));
        dataStore.put("k3", new Data(3, data));
    }
}
