package dao;

import br.com.senac.checkpets.CheckPets;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import model.Pets;

/**
 *
 * @Fernando Henry
 */
public class PetsDao {

    public void salvar(Pets pets) {
        EntityManager em = CheckPets.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pets);
            em.getTransaction().commit();
        } catch (Exception e) {
            // Se der qualquer erro, desfaz a transação
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            // Imprime o erro no console para debug
            System.err.println("Erro ao salvar o pet: " + e.getMessage());
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
     * Busca um Pet no banco de dados pelo seu ID (chave primária).
     * @param id O ID do pet a ser encontrado.
     * @return O objeto Pet encontrado, ou null se não existir um pet com esse ID.
     */
    public Pets findById(int id) {
        EntityManager em = CheckPets.getEntityManager();
        try {
            // O método em.find() é a forma padrão e mais eficiente do JPA
            // para buscar uma entidade pela sua chave primária.
            Pets pet = em.find(Pets.class, id);
            return pet;
        } finally {
            // Garante que o EntityManager será fechado ao final da operação.
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}
