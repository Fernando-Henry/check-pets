package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Representa a entidade Consulta, mapeando a tabela 'consulta' do banco de dados.
 */
@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConsulta;

    private boolean realizada;
    
    private String medicacao;

    // Relação Muitos-para-Um: Muitas consultas podem ser feitas por um Pet.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "petId_idPet") // Nome da coluna FK no banco
    private Pets pet;

    // Relação Muitos-para-Um: Muitas consultas podem ser realizadas por um Veterinário.   
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "veterinarioMatricula_matriculaVeterinario") // Nome da coluna FK no banco
    private Funcionario veterinario;

    
    // --- Getters e Setters ---

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public String getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(String medicacao) {
        this.medicacao = medicacao;
    }

    public Pets getPet() {
        return pet;
    }

    public void setPet(Pets pets) {
        this.pet = pets;
    }

    public Funcionario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Funcionario veterinario) {
        this.veterinario = veterinario;
    }
}