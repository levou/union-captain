package com.xishui.union.captain.factory;

import com.xishui.union.captain.bus.TyreComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UnionCaptainFactorySpringApplication {

    public static void main(String... args) {
        ConfigurableApplicationContext context = SpringApplication.run(UnionCaptainFactorySpringApplication.class, args);

        TyreComponent component = context.getBean(TyreComponent.class);
        component.mulitTyre("spring mulit tyre");
        component.tyre("spring tyre");
//        SingleComponent singleComponent = ComponentBoot.BOOT.getComponent(SingleComponent.class);
//        singleComponent.single("外部jar-springboot");
    }
}
