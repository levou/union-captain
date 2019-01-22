package com.kxl.union.captain.bus;

import com.kxl.union.captain.component.ComponentConfig;
import lombok.Data;

@Data
public class BusComponentConfig implements ComponentConfig {
    private String busName;
}
