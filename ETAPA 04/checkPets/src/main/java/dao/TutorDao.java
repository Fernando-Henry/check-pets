package dao;

import br.com.senac.checkpets.CheckPets;
import jakarta.persistence.EntityManager;
import model.Pets;
import model.Tutor;

public class TutorDao {

    public void salvar(Tutor tutor) {
        EntityManager em = CheckPets.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tutor);
            em.getTransaction().commit();
        } catch (Exception e) {
            // Se der qualquer erro, desfaz a transação
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            // Imprime o erro no console para debug
            System.err.println("Erro ao salvar o tutor: " + e.getMessage());
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
     * Busca um Tutor no banco de dados pelo seu ID.
     *
     * @param id O ID do tutor a ser encontrado.
     * @return O objeto Tutor encontrado, ou null se não existir.
     */
    public Tutor findById(int id) {
        EntityManager em = CheckPets.getEntityManager();
        try {
            // O método find é a forma padrão do JPA para buscar pela chave primária
            Tutor tutor = em.find(Tutor.class, id);
            return tutor;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }

    }
}
