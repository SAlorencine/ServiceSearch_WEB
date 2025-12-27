

import com.mycompany.pi.Validador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidadorTest {

    public ValidadorTest() {
    }

    @Test
    public void testValidarSenha() {
        System.out.println("Validar Senha");
        Validador validador = new Validador();
        
        assertTrue(validador.validarSenha("123456"));
        assertTrue(validador.validarSenha("minhasenhaforte"));
        
        assertFalse(validador.validarSenha("123"));
        assertFalse(validador.validarSenha(""));
        
        assertFalse(validador.validarSenha(null));
    }

    @Test
    public void testValidarEmail() {
        System.out.println("Validar Email");
        Validador validador = new Validador();
        
        assertTrue(validador.validarEmail("joao@email.com"));
        
        assertFalse(validador.validarEmail("joaoemail.com"));
        assertFalse(validador.validarEmail("joao@emailcom"));
    }
}