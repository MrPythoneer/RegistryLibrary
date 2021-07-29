package me.joshua.windows.registry;

public record RegKey(String path) {
    public RegKey getKey(String name) {
        return new RegKey(path + "\\" + name);
    }

    public RegValue getValue(String name) {
        return new RegValue(path, name);
    }

    public void export(String outputPath) {
        CmdOutput.executeCmd("REG", "EXPORT", path, outputPath, "/Y");
    }

    public void create() {
        CmdOutput.executeCmd("REG", "ADD", path, "/F");
    }

    public void delete() {
        CmdOutput.executeCmd("REG", "DELETE", path, "/F");
    }

    public boolean exists() {
        final CmdOutput cmdOutput = CmdOutput.executeCmd("REG", "QUERY", path);
        if (cmdOutput == null)
            return false;

        return cmdOutput.exitCode() == 0;
    }
}
