package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDate;

/**
 * Representa a entidade Atendente, que é um tipo de Funcionário. A anotação
 * @DiscriminatorValue("ATE") define a "etiqueta" que será salva na coluna DTYPE
 * da tabela Funcionario para identificar este tipo.
 */
@Entity
@DiscriminatorValue("ATE")
public class Atendente extends Funcionario {

    /**
     * Construtor padrão.
     */
    public Atendente() {
        super();
    }

    /**
     * Construtor completo que repassa todos os valores para o construtor da
     * superclasse Funcionario.
     *
     * @param matricula A matrícula do funcionário.
     * @param nome O nome do funcionário.
     * @param dataNascimento A data de nascimento do funcionário.
     * @param telefone O telefone de contato.
     * @param endereco O objeto Endereco associado.
     */
    // Este construtor deve estar na sua classe filha (ex: Atendente.java)
    public Atendente(int matricula, String nome, String telefone, LocalDate dataNascimento, Cargo cargo, Endereco endereco) {
        // A chamada super() agora tem 7 argumentos, correspondendo exatamente
        // ao construtor que existe na classe Funcionario.
        super(matricula, nome, telefone, null, dataNascimento, cargo, endereco);

        // Se a classe Atendente tivesse campos próprios, eles seriam inicializados aqui.
    }

    // --- MÉTODOS DE NEGÓCIO ESPECÍFICOS DO ATENDENTE ---
    public void receberPagamento() {
        System.out.println("O atendente " + getNome() + " está recebendo um pagamento.");
        // Lógica para processar pagamentos...
    }

    public void buscarPet() {
        System.out.println("O atendente " + getNome() + " está buscando um pet no sistema.");
        // Lógica para a busca de pets...
    }

    public void agendamentoDeConsulta() {
        System.out.println("O atendente " + getNome() + " está agendando uma nova consulta.");
        // Lógica para agendamentos...
    }

    public void cancelamentoDeConsulta() {
        System.out.println("O atendente " + getNome() + " está cancelando uma consulta.");
        // Lógica para cancelamentos...
    }
}
