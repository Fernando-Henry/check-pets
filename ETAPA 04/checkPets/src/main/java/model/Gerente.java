package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDate;

/**
 * Representa um Gerente. No modelo de Composição, esta classe serve como uma
 * "fábrica" para criar objetos Funcionario com os dados corretos de um gerente.
 * Ela herda de Funcionario para poder ser tratada como um tipo de funcionário.
 */

@Entity
@DiscriminatorValue("GER")
public class Gerente extends Funcionario {

    /**
     * Construtor padrão.
     */
    public Gerente() {
        super();
    }

    /**
     * Construtor completo para criar um Gerente.
     * @param matricula A matrícula do funcionário.
     * @param nome O nome do funcionário.
     * @param telefone O telefone de contato.
     * @param dataNascimento A data de nascimento.
     * @param cargo O objeto Cargo (deve ser o de "Gerente").
     * @param endereco O objeto Endereco associado.
     */
    public Gerente(int matricula, String nome, String telefone,
                   LocalDate dataNascimento, Cargo cargo, Endereco endereco) {
        
        // Chama o construtor completo de Funcionario, passando os valores específicos.
        // Como um Gerente NÃO é um veterinário, passamos 'null' para o campo crmv.
        super(matricula, nome, telefone, null, dataNascimento, cargo, endereco);
    }
    
    // --- MÉTODOS DE NEGÓCIO ESPECÍFICOS DO GERENTE ---

    public void cadastrarAtendente() {
        System.out.println("O gerente " + getNome() + " está cadastrando um novo atendente.");
    }

    public void cadastrarVeterinario() {
        System.out.println("O gerente " + getNome() + " está cadastrando um novo veterinário.");
    }
}