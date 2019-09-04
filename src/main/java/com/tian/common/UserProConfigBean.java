package com.tian.common;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;

/**
 * Created by z on 2019/9/4.
 */
public class UserProConfigBean {
    /**
     * 这里添加了一个监听事件,当配置中心的值有改变,并发布的时候,这里会收到通知.所以
     * jvm中配置的值会实时刷新.
     * @param configChangeEvent
     */
    private void someoneChange(ConfigChangeEvent configChangeEvent){
        configChangeEvent.changedKeys().forEach(key -> {
            ConfigChange change = configChangeEvent.getChange(key);
            System.out.println(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s",
                    change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
        });
    }

}
