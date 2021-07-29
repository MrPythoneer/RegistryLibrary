package me.joshua.windows.registry.types;

import me.joshua.windows.registry.CmdOutput;
import me.joshua.windows.registry.RegValue;

public final class RegQword extends RegValue {

    public RegQword(String keyPath, String name) {
        super(keyPath, name);
    }

    public Integer data() {
        try {
            final String value = rawData();
            if (value == null) return null;

            return Integer.parseInt(value.substring(2), 16);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public boolean setData(int value) {
        final String formattedValue = String.valueOf(value);
        final CmdOutput cmdOutput = CmdOutput.executeCmd("REG", "ADD", keyPath(), "/V", name(), "/T", "REG_QWORD", "/D", formattedValue, "/F");
        if (cmdOutput == null)
            return false;

        return cmdOutput.exitCode() == 0;
    }

    @Override
    public String type() {
        return "REG_QWORD";
    }
}
