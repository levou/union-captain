package com.kxl.union.captain.factory;

import com.kxl.union.captain.bus.TyreComponent;
import com.kxl.union.captain.component.ComponentBoot;
import com.kxl.union.comp.single.component.SingleComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;

@SpringBootApplication
public class UnionCaptainFactorySpringApplication {

    public static void main(String... args) {
        ConfigurableApplicationContext context = SpringApplication.run(UnionCaptainFactorySpringApplication.class, args);

        TyreComponent component = context.getBean(TyreComponent.class);
        component.mulitTyre("spring mulit tyre");
        component.tyre("spring tyre");
        SingleComponent singleComponent = ComponentBoot.BOOT.getComponent(SingleComponent.class);
        singleComponent.single("外部jar-springboot");
    }
}
