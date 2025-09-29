package dao;

import br.com.senac.checkpets.CheckPets;
import model.Cargo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class CargoDao {

 
    public Cargo buscarPorNome(String funcao) {
        EntityManager em = CheckPets.getEntityManager();
        
        try {
            TypedQuery<Cargo> query = em.createQuery(
                    "SELECT c FROM Cargo c WHERE c.funcao = :funcao", Cargo.class);
            query.setParameter("funcao", funcao);
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Retorna nulo se não encontrar nenhum cargo com esse nome
            return null;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}
