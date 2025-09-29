package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;

/**
 *
 * @author FernandoHenry
 */
@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matricula;

    @Column(name = "nomeCompleto")
    private String nome;

    @Column(name = "telefoneContato")
    private String telefone;

    private String crmv;
    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name = "Cargo_idCargo")
    private Cargo cargo;

    // Um funcionário tem um único endereço
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "Endereco_idEndereco")
    private Endereco endereco;

    public Funcionario() {
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Funcionario(int matricula, String nome, String telefone, String crmv, LocalDate dataNascimento, Cargo cargo, Endereco endereco) {
        this.matricula = matricula;
        this.nome = nome;
        this.telefone = telefone;
        this.crmv = crmv;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
        this.endereco = endereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
