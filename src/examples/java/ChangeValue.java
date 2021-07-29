import me.joshua.windows.registry.RegKey;
import me.joshua.windows.registry.RegValue;
import me.joshua.windows.registry.types.RegQword;
import me.joshua.windows.registry.types.RegSz;

public class ChangeValue {
    public static void main(String[] args) {
        final RegKey key = new RegKey("HKEY_CURRENT_USER\\JavaRegistryLibrary\\Test");
        final RegValue myValue = key.getValue("MyValue");

        final RegSz myValueAsRegSz = myValue.asRegSz();
        myValueAsRegSz.setData("This is my value");
        System.out.println(myValueAsRegSz.data());

        // Changing type and data
        final RegQword myValueAsRegQword = myValue.asRegQword();
        myValueAsRegQword.setData(666);
        System.out.println(myValueAsRegQword.data());
    }
}
