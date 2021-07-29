package me.joshua.windows.registry.types;

import me.joshua.windows.registry.CmdOutput;
import me.joshua.windows.registry.RegValue;

public final class RegExpandSz extends RegValue {

    public RegExpandSz(String keyPath, String name) {
        super(keyPath, name);
    }

    public String data() {
        return rawData();
    }

    public boolean setData(String value) {
        final CmdOutput cmdOutput = CmdOutput.executeCmd("REG", "ADD", keyPath(), "/V", name(), "/T", "REG_EXPAND_SZ", "/D", value, "/F");
        if (cmdOutput == null)
            return false;

        return cmdOutput.exitCode() == 0;
    }

    @Override
    public String type() {
        return "REG_EXPAND_SZ";
    }
}
