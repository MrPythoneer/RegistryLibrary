package me.joshua.windows.registry.types;

import me.joshua.windows.registry.CmdOutput;
import me.joshua.windows.registry.RegValue;

public class RegValueParser {
    private final String name;
    private final String type;
    private final String value;

    public RegValueParser(CmdOutput cmdOutput) {
        final String dataLine = cmdOutput.text().split("\r\n")[2];
        final String[] data = dataLine.split("\s{4,}");

        this.name = data[1].strip();
        this.type = data[2].strip();
        this.value = data[3].strip();
    }

    public static RegValueParser parseValue(RegValue value) {
        return parseValue(value.keyPath(), value.name());
    }

    public static RegValueParser parseValue(String keyPath, String name) {
        final CmdOutput output = CmdOutput.executeCmd("REG", "QUERY", keyPath, "/V", name);
        if (output == null)
            return null;

        return new RegValueParser(output);
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public String data() {
        return value;
    }
}
