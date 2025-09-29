package br.com.senac.checkpets;

import dao.PetService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Pets;

/**
 *
 * @author Fernando
 */
public class CheckPets {

    private static final EntityManagerFactory fabricaEntidade
            = Persistence.createEntityManagerFactory("CheckPets-PU");

    public static EntityManager getEntityManager() {
        return fabricaEntidade.createEntityManager();
    }
}
