
package model;

import jakarta.persistence.Entity;           // anotação para marcar a classe como entidade JPA
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;               // anotação que marca a chave primária
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;            // anotação para mapear a tabela no banco

/**
 *
 * @author FernandoHenry
 */

@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matriculaFuncionario;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "Funcionario_matriculaFuncionario")
    private Funcionario funcionario;
    
    private String login;
    private String senha;

    public Usuario(int matriculaFuncionario, String login, String senha) {
        this.matriculaFuncionario = matriculaFuncionario;
        this.login = login;
        this.senha = senha;
    }

    public Usuario() {
    }

    public int getMatriculaFuncionario() {
        return matriculaFuncionario;
    }

    public void setMatricula(int matricula) {
        this.matriculaFuncionario = matricula;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    
    
}
