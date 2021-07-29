package me.joshua.windows.registry;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public record CmdOutput(int exitCode, String text) {
    public static CmdOutput executeCmd(String... command) {
        try {
            final ProcessBuilder builder = new ProcessBuilder(command);
            final Process exec = builder.start();
            exec.waitFor(2, TimeUnit.SECONDS);
            exec.destroy();
            return new CmdOutput(exec.exitValue(), new String(exec.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "CmdOutput(%d, %s)".formatted(exitCode, text.replaceAll("[\n\r]", "").substring(0, 47) + "...");
    }
}
