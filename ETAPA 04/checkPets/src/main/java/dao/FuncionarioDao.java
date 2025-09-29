package dao;

import jakarta.persistence.EntityManager;
import model.Funcionario;


import br.com.senac.checkpets.CheckPets; 

public class FuncionarioDao {

    /**
     * Salva (persiste) um novo objeto Funcionario no banco de dados.
     * Este método também salvará o Endereco associado, por causa do cascade.
     * @param funcionario O objeto Funcionario, já preenchido e com as associações.
     */
    public void salvar(Funcionario funcionario) {
        EntityManager em = CheckPets.getEntityManager(); // Obtém o EntityManager
        try {
            em.getTransaction().begin();    // Inicia a transação
            em.persist(funcionario);        // Persiste o objeto funcionário (e o endereço em cascata)
            em.getTransaction().commit();   // Confirma a transação
        } catch (Exception e) {
            // Se der qualquer erro, desfaz a transação
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            // Imprime o erro no console para debug
            System.err.println("Erro ao salvar o funcionário: " + e.getMessage());
            // Opcional: Lançar a exceção para que a tela possa tratá-la
            // throw new RuntimeException("Erro ao salvar funcionário", e);
            throw e;
        } finally {
            // Garante que o EntityManager será fechado ao final
            if (em.isOpen()) {
                em.close();
            }
        }
    }
    
    /**
     * Busca um Funcionario pela sua matrícula.
     * @param matricula A matrícula (chave primária) do funcionário.
     * @return O objeto Funcionario encontrado, ou null se não encontrar.
     */
    public Funcionario buscarPorMatricula(int matricula) {
        EntityManager em = CheckPets.getEntityManager();
        try {
            // .find é a forma mais simples de buscar pela chave primária
            return em.find(Funcionario.class, matricula);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Atualiza os dados de um Funcionario existente no banco.
     * @param funcionario O objeto Funcionario com os dados atualizados.
     */
    public void atualizar(Funcionario funcionario) {
        EntityManager em = CheckPets.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(funcionario); // 'merge' é usado para atualizar
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao atualizar o funcionário: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
    
    // Você pode adicionar outros métodos aqui, como listarTodos() ou excluir().
}