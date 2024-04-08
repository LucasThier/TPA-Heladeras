import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ValidadorDeContraseñas {

        private static final String ArchivoContrasDebiles = "./Recursos/ContrasComunes.txt";

        private static Set<String> CargarContras() throws IOException {
            Set<String> ContrasDebiles = new HashSet<>();
            BufferedReader Lector = new BufferedReader(new FileReader(ArchivoContrasDebiles));
            String linea;
            while ((linea = Lector.readLine()) != null) {
                ContrasDebiles.add(linea.trim());
            }
            Lector.close();
            return ContrasDebiles;
        }

        public static boolean EsContraseñaValida(String Contraseña) throws IOException {

            // Verificar longitud mínima
            if (Contraseña.length() < 8) {
                return false;
            }

            // Verificar al menos una mayúscula y un número
            boolean TieneMayuscula = false;
            boolean TieneNumero = false;
            for (char c : Contraseña.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    TieneMayuscula = true;
                }
                if (Character.isDigit(c)) {
                    TieneNumero = true;
                }
            }
            if (!TieneMayuscula || !TieneNumero) {
                return false;
            }

            Set<String> ContraseñasDebiles = CargarContras();

            return !ContraseñasDebiles.contains(Contraseña);
        }



        public static void main(String[] args) {
            try {
                String Contraseña = "12345678";
                if (EsContraseñaValida(Contraseña)) {
                    System.out.println("La contraseña es segura.");
                } else {
                    System.out.println("La contraseña es débil. Por favor, elija una contraseña más segura.");
                }
            } catch (IOException e) {
                System.err.println("Error al cargar el archivo de contraseñas débiles.");
                e.printStackTrace();
            }
        }

}
