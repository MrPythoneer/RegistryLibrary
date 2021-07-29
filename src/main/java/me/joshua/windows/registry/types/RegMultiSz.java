package me.joshua.windows.registry.types;

import me.joshua.windows.registry.CmdOutput;
import me.joshua.windows.registry.RegValue;

public final class RegMultiSz extends RegValue {

    public RegMultiSz(String keyPath, String name) {
        super(keyPath, name);
    }

    public String data() {
        return rawData();
    }

    public boolean setData(String value) {
        final CmdOutput cmdOutput = CmdOutput.executeCmd("REG", "ADD", keyPath(), "/V", name(), "/T", "REG_MULTI_SZ", "/D", value, "/F");
        if (cmdOutput == null)
            return false;

        return cmdOutput.exitCode() == 0;
    }

    @Override
    public String type() {
        return "REG_MULTI_SZ";
    }
}
