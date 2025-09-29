package dao;

import jakarta.persistence.EntityManager;
import model.Endereco;
// Substitua 'br.com.senac.checkpets.CheckPets' pela sua classe utilitária de JPA, se o nome for diferente.
import br.com.senac.checkpets.CheckPets;

public class EnderecoDao {

    /**
     * Salva um novo objeto Endereco no banco de dados.
     * @param endereco O objeto Endereco a ser persistido.
     */
    public void salvar(Endereco endereco) {
        EntityManager em = CheckPets.getEntityManager(); // Obtém o EntityManager
        try {
            em.getTransaction().begin(); // Inicia a transação
            em.persist(endereco);        // Persiste o objeto (cria o novo registro)
            em.getTransaction().commit();  // Confirma a transação
        } catch (Exception e) {
            // Se der erro, desfaz a transação
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar o endereço: " + e.getMessage());
            // Opcional: Lançar a exceção para a camada de cima tratar
            // throw e;
        } finally {
            // Garante que o EntityManager será fechado
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Busca um Endereco pelo seu ID.
     * @param id O ID do endereço a ser buscado.
     * @return O objeto Endereco encontrado, ou null se não encontrar.
     */
    public Endereco buscarPorId(int id) {
        EntityManager em = CheckPets.getEntityManager();
        try {
            return em.find(Endereco.class, id);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Atualiza um objeto Endereco existente no banco de dados.
     * @param endereco O objeto Endereco com os dados atualizados.
     */
    public void atualizar(Endereco endereco) {
        EntityManager em = CheckPets.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(endereco); // 'merge' é usado para atualizar uma entidade existente
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao atualizar o endereço: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Exclui um Endereco do banco de dados pelo seu ID.
     * @param id O ID do endereço a ser excluído.
     */
    public void excluir(int id) {
        EntityManager em = CheckPets.getEntityManager();
        try {
            em.getTransaction().begin();
            Endereco endereco = em.find(Endereco.class, id);
            if (endereco != null) {
                em.remove(endereco); // Remove a entidade
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao excluir o endereço: " + e.getMessage());
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}