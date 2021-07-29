package me.joshua.windows.registry.types;

import me.joshua.windows.registry.CmdOutput;
import me.joshua.windows.registry.RegValue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegBinary extends RegValue {
    private static final Pattern READER = Pattern.compile("..");

    public RegBinary(String keyPath, String name) {
        super(keyPath, name);
    }

    public byte[] data() {
        final String value = rawData();

        final byte[] bytes = new byte[value.length() / 2];
        final Matcher matcher = READER.matcher(value);

        int i = 0;
        while (matcher.find())
            bytes[i++] = (byte) Integer.parseInt(matcher.group(), 16);

        return bytes;
    }

    public boolean setData(byte[] value) {
        final StringBuilder sb = new StringBuilder();
        for (byte b : value)
            sb.append(String.format("%02X", b));

        final CmdOutput cmdOutput = CmdOutput.executeCmd("REG", "ADD", keyPath(), "/V", name(), "/T", "REG_BINARY", "/D", sb.toString(), "/F");
        if (cmdOutput == null)
            return false;

        return cmdOutput.exitCode() == 0;
    }

    @Override
    public String type() {
        return "REG_BINARY";
    }
}

