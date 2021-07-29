package me.joshua.windows.registry;

import me.joshua.windows.registry.types.*;

public class RegValue {
    private final String keyPath;
    private final String name;

    public RegValue(String keyPath, String name) {
        this.keyPath = keyPath;
        this.name = name;
    }

    public String rawName() {
        final RegValueParser parser = RegValueParser.parseValue(this);
        if (parser == null)
            return null;

        return parser.name();
    }

    public String rawType() {
        final RegValueParser parser = RegValueParser.parseValue(this);
        if (parser == null)
            return null;

        return parser.type();
    }

    public String rawData() {
        final RegValueParser parser = RegValueParser.parseValue(this);
        if (parser == null)
            return null;

        return parser.data();
    }

    public String type() {
        final RegValueParser parser = RegValueParser.parseValue(keyPath, name);
        if (parser == null)
            return null;

        return parser.type();
    }

    public String keyPath() {
        return keyPath;
    }

    public String name() {
        return name;
    }

    public void delete() {
        CmdOutput.executeCmd("REG", "DELETE", keyPath(), "/V", name(), "/F");
    }

    public RegSz asRegSz() {
        return new RegSz(keyPath, name);
    }

    public RegDword asRegDword() {
        return new RegDword(keyPath, name);
    }

    public RegQword asRegQword() {
        return new RegQword(keyPath, name);
    }

    public RegMultiSz asRegMultiSz() {
        return new RegMultiSz(keyPath, name);
    }

    public RegBinary asRegBinary() {
        return new RegBinary(keyPath, name);
    }

    public RegExpandSz asRegExpandSz() {
        return new RegExpandSz(keyPath, name);
    }
}
