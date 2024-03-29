package com.xishui.union.captain.factory;

import com.xishui.union.captain.component.Component;

import java.util.ServiceLoader;

public class SpiTest {

    public static void main(String... args) {
        ServiceLoader<Component> serviceLoader = ServiceLoader.load(Component.class, System.class.getClassLoader());
        serviceLoader.iterator().forEachRemaining(service->System.out.println(service.getClass().getName()));
    }
}
