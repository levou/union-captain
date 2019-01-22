package com.xishui.union.captain.common;

import java.util.HashMap;
import java.util.Map;

public class MapNullTest {

    public static void main(String... args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "24");
        map.put("b", null);
        map.put(null, null);

        map.forEach((k,v)->System.out.println(k+" "+v));
    }
}
