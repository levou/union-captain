package com.xishui.union.captain.component.transfer;

import com.xishui.union.captain.common.logger.CaptainLogger;
import com.xishui.union.captain.component.ComponentDefine;
import com.xishui.union.captain.component.exception.ComponentDefineException;
import com.xishui.union.captain.component.holder.DefineHolders;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * 将url文件读取，并转化为set defines
 */

public enum ComponentDefineTransfer {
    TRANSFER;

    public static final String COMPONENT_DEFINE_SPLITER = "=";

    public void transfer(Set<URL> urls) throws ComponentDefineException {
        final Set<ComponentDefine> defines = new HashSet<>();
        urls.forEach(url -> {
            try {
                defines.addAll(loaderDefine(url));
            } catch (Exception e) {
                CaptainLogger.error(this.getClass(), url + " Define Transfer Err:" + e.getMessage());
                throw new ComponentDefineException(e);
            }
        });
        DefineHolders.HOLDERS.addDefines(defines);
    }


    private Set<ComponentDefine> loaderDefine(final URL url) throws Exception {
        InputStream inputStream = null;
        String line = null;
        final Set<ComponentDefine> componentDefines = new HashSet<>();
        try {
            inputStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (null != (line = reader.readLine())) {
                if (null == line || "".equals(line.trim()) || line.trim().startsWith("#")) {
                    continue;
                }
                componentDefines.add(ComponentDefine.builder(line));
                CaptainLogger.info(this.getClass(), "Define Transfer Success,Line : " + line);
            }
        }catch (Exception e){
        }
        finally {
            if (null != inputStream) {
                inputStream.close();
            }
        }
        return componentDefines;
    }
}
