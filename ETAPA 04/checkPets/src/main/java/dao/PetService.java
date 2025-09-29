package dao;

import jakarta.persistence.EntityManager;
import model.Pets;

public class PetService {

    // O método agora recebe o EntityManager.
    public Pets cadastrarPet(EntityManager em, Pets pet) {
        
        try {
            // Inicia uma transação (como um "agrupamento" de operações).
            em.getTransaction().begin();

            // Salva o objeto 'pet' no banco de dados.
            em.persist(pet);

            // Confirma a transação, salvando as alterações de forma definitiva.
            em.getTransaction().commit();

        } catch (Exception e) {
            // Em caso de erro, desfaz a transação para evitar dados incompletos.
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } 
        
        return pet;
    }
}