package com.zsb.bluex.core.groovy;

import groovy.lang.GroovyClassLoader;

public class GroovyScriptLoader {

    private final GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

    public Class<?> loadScript(String scriptCode) throws Exception {
        return groovyClassLoader.parseClass(scriptCode);
    }
}
