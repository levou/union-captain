package com.xishui.union.captain.factory;

import com.xishui.union.captain.bus.MotorComponent;
import com.xishui.union.captain.bus.TyreComponent;
import com.xishui.union.captain.component.ComponentBoot;
import com.xishui.union.captain.plane.CabinComponent;
import com.xishui.union.captain.plane.ChairComponent;

public class UnionCaptainFactoryMain {
    public static void main(String... args){
        try {
            ComponentBoot.BOOT.starter();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //汽车
        ComponentBoot.BOOT.getComponent(TyreComponent.class).tyre("汽车轮胎");
        ComponentBoot.BOOT.getComponent(TyreComponent.class).mulitTyre("汽车轮胎");
        ComponentBoot.BOOT.getComponent(MotorComponent.class).motor("汽车玻璃");
        //飞机
        ComponentBoot.BOOT.getComponent(CabinComponent.class).cabin("飞机机仓");
        ComponentBoot.BOOT.getComponent(ChairComponent.class).chair("飞机椅子");
//        ComponentBoot.BOOT.getComponent(SingleComponent.class).single("外部的jar包引入");
    }
}
