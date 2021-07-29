import me.joshua.windows.registry.RegKey;
import me.joshua.windows.registry.RegValue;

public class ChangeValue {
    public static void main(String[] args) {
        final RegKey key = new RegKey("HKEY_CURRENT_USER\\JavaRegistryLibrary\\Test");
        final RegValue myValue = key.getValue("MyValue");

        myValue.asRegSz().setData("This is my value");
        System.out.println(myValue.asRegSz().data());

        // Changing type and data
        myValue.asRegQword().setData(666);
        System.out.println(myValue.asRegQword().data());
    }
}
