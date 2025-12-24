

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
        
        // Cenário Positivo: Senha com 6 ou mais caracteres deve retornar true
        assertTrue(validador.validarSenha("123456"));
        assertTrue(validador.validarSenha("minhasenhaforte"));
        
        // Cenário Negativo: Senha curta deve retornar false
        assertFalse(validador.validarSenha("123"));
        assertFalse(validador.validarSenha(""));
        
        // Cenário de Null
        assertFalse(validador.validarSenha(null));
    }

    @Test
    public void testValidarEmail() {
        System.out.println("Validar Email");
        Validador validador = new Validador();
        
        // E-mail válido
        assertTrue(validador.validarEmail("joao@email.com"));
        
        // E-mail inválido (sem @ ou sem ponto)
        assertFalse(validador.validarEmail("joaoemail.com"));
        assertFalse(validador.validarEmail("joao@emailcom"));
    }
}