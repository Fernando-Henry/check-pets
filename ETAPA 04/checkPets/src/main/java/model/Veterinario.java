
package model;

/**
 *
 * @author FernandoHenry
 */
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDate;

/**
 * Representa a entidade Veterinario, que é uma especialização de Funcionário.
 */
import java.time.LocalDate;

/**
 * Representa um Veterinário, que é um tipo de Funcionário.
 * Esta classe herda todos os campos de Funcionário, incluindo 'crmv'.
 * Ela pode ter métodos específicos que outros funcionários não têm.
 */
@Entity
@DiscriminatorValue("VET")
public class Veterinario extends Funcionario {

    /**
     * Construtor do Veterinário.
     * Note que ele não tem campos próprios, apenas passa os dados para o
     * construtor da superclasse Funcionario.
     */
    public Veterinario(String crmv, int matricula, String nome, LocalDate dataNascimento, String telefone, Cargo cargo, Endereco endereco) {
        // Agora a chamada super() corresponde EXATAMENTE ao construtor da classe Funcionario,
        // passando todos os 7 argumentos na ordem correta.
        super(matricula, nome, telefone, crmv, dataNascimento, cargo, endereco);
    }
      
    public Veterinario() {
        super();
    }


    public void realizarConsulta() {
        // getNome() e getCrmv() são métodos herdados de Funcionario
        System.out.println("O veterinário " + getNome() + " (CRMV: " + getCrmv() + ") está realizando uma consulta.");
    }
}
