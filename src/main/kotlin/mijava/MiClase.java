package mijava;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MiClase {

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