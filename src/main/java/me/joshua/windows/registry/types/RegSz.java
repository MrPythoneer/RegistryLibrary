package me.joshua.windows.registry.types;

import me.joshua.windows.registry.CmdOutput;
import me.joshua.windows.registry.RegValue;

public final class RegSz extends RegValue {
    public RegSz(String keyPath, String name) {
        super(keyPath, name);
    }

    public String data() {
        return rawData();
    }

    public boolean setData(String value) {
        final CmdOutput cmdOutput = CmdOutput.executeCmd("REG", "ADD", keyPath(), "/V", name(), "/T", "REG_SZ", "/D", value, "/F");
        if (cmdOutput == null)
            return false;

        return cmdOutput.exitCode() == 0;
    }

    @Override
    public String type() {
        return "REG_SZ";
    }
}
