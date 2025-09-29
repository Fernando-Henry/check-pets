package dao;

import model.Veterinario;
import br.com.senac.checkpets.CheckPets;
import jakarta.persistence.EntityManager;

public class VeterinarioDao {

    public void salvar(Veterinario veterinario) {
        EntityManager em = CheckPets.getEntityManager(); // Obtém o EntityManager
        try {
            em.getTransaction().begin();    // Inicia a transação
            em.persist(veterinario);        // Persiste o objeto funcionário (e o endereço em cascata)
            em.getTransaction().commit();   // Confirma a transação
        } catch (Exception e) {
            // Se der qualquer erro, desfaz a transação
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            // Imprime o erro no console para debug
            System.err.println("Erro ao salvar o veterinario: " + e.getMessage());
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
     * Busca um Veterinario no banco de dados pelo seu ID.
     * @param id O ID do veterinário a ser encontrado.
     * @return O objeto Veterinario encontrado, ou null se não existir.
     */
    public Veterinario findById(int id) {
        EntityManager em = CheckPets.getEntityManager();
        try {
            // A "mágica" acontece aqui: pedimos especificamente pela classe Veterinario.
            // O JPA cuida da lógica de herança por baixo dos panos.
            Veterinario vet = em.find(Veterinario.class, id);
            return vet;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    // Você poderia adicionar outros métodos aqui no futuro, como:
    // public List<Veterinario> findByEspecialidade(String especialidade) { ... }
    // public Veterinario findByCrmv(String crmv) { ... }
}