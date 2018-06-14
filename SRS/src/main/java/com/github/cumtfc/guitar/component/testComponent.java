package com.github.cumtfc.guitar.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "test.com.github.cumtfc.guitar.component")
public class testComponent {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        System.out.println("testComponent:id:" + id);
        this.id = id;
    }
}
