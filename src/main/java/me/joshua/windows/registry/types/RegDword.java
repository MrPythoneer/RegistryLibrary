package me.joshua.windows.registry.types;

import me.joshua.windows.registry.CmdOutput;
import me.joshua.windows.registry.RegValue;

public final class RegDword extends RegValue {
    public RegDword(String keyPath, String name) {
        super(keyPath, name);
    }

    public Long data() {
        try {
            final String value = rawData();
            if (value == null)
                return null;

            return Long.parseLong(value.substring(2), 16);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            return null;
        }
    }

    public boolean setData(long value) {
        final String formattedValue = String.valueOf(value);
        final CmdOutput cmdOutput = CmdOutput.executeCmd("REG", "ADD", keyPath(), "/V", name(), "/T", "REG_DWORD", "/D", formattedValue, "/F");
        if (cmdOutput == null)
            return false;

        return cmdOutput.exitCode() == 0;
    }

    @Override
    public String type() {
        return "REG_DWORD";
    }
}
