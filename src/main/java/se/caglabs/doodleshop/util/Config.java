package se.caglabs.doodleshop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

public class Config {
    private String backgroundColor;

    @Autowired
    private Environment environment;

    @PostConstruct
    private void init() {
        backgroundColor = environment.getProperty("se.caglabs.doodleshop.backgroundcolor");
    }


    public String getBackgroundColor() {
        return backgroundColor;
    }
}