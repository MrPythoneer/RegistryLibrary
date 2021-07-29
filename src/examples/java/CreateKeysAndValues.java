import me.joshua.windows.registry.RegKey;

public class CreateKeysAndValues {
    public static void main(String[] args) {
        // Keys and values will be forcibly created so there's no need to check if they exist.
        final RegKey key = new RegKey("HKEY_CURRENT_USER\\JavaRegistryLibrary\\Test");

        key.getValue("MyValue").asRegSz().setData("This is my value");
        System.out.println(key.getValue("MyValue").asRegSz().data());
    }
}
