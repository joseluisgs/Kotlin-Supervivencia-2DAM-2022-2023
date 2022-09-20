package mijava;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
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

    public void ejemploVarianza() {
        // Object < -- Integer
        // List<Object> < -- List<Integer>

        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        // Si el lenguaje permitiese esto podría tener esto
        //List<Object> listaObjetos = lista;
        //listaObjetos.add("Hola");
        // Pero esto no es posible en Java
        //listaObjetos.add("Hello");
        var i = lista.get(3); // como que tengo un String en algo de integer Error en compilacion
        System.out.println(i);

        // NO!!!! es un subtipo

        // Ahora prueba esto y piensa que pasa si no me avisa el compilador
        // o lo intento engañar

        List<Integer> lista2 = List.of(1, 2, 3);
        List<Object> listaObjetos2 = new ArrayList<>(Collections.singletonList(lista2));
        listaObjetos2.add("Hola");
        var i2 = lista2.get(3); // ??
        System.out.println(i2);


    }

}
