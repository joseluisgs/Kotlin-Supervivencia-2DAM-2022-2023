package mijava;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MiClase {

    public List<String> lista = List.of("uno", "dos", "tres");

    public String getStringNullable() {
        return null;
    }

    public String getStringNotNull() {
        return "Hello";
    }

    @Nullable // Puede devolverme un nulo
    public String getStringNullableWithNotation() {
        return null;
    }

    @NotNull // nunca devolverá un nulo
    public String getStringNotNullNotation() {
        return "Hello";
    }

    public void printString(@NotNull String string) {
        System.out.println(string);
    }

    public void throwException() throws Exception {
        throw new Exception("Error");
    }

}
