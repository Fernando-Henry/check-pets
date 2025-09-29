package dao;

import br.com.senac.checkpets.CheckPets;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.Consulta;

/**
 * DAO (Data Access Object) para a entidade Consulta. Esta classe é responsável
 * por todas as interações (salvar, buscar, atualizar, deletar) com a tabela de
 * consultas no banco de dados.
 *
 * @author Fernando Henry
 */
public class ConsultaDao {

    /**
     * Persiste (salva) uma nova instância da entidade Consulta no banco de
     * dados.
     *
     * @param consulta O objeto Consulta, já preenchido, que deve ser salvo.
     */
    public void salvar(Consulta consulta) {
        // Obtém uma instância do EntityManager a partir da classe utilitária CheckPets.
        // O EntityManager é o objeto principal do JPA para interagir com o banco de dados.
        EntityManager em = CheckPets.getEntityManager();

        // Inicia um bloco 'try-catch-finally' para garantir que os recursos sejam
        // sempre liberados, mesmo que ocorram erros.
        try {
            // Inicia uma nova transação com o banco de dados. Todas as operações de escrita
            // (insert, update, delete) devem ocorrer dentro de uma transação.
            em.getTransaction().begin();

            // Pede ao JPA para gerenciar o objeto 'consulta'. Neste momento, se o objeto é novo,
            // ele é agendado para ser inserido no banco de dados.
            em.persist(consulta);

            // Confirma a transação. Se nenhuma exceção ocorreu, todas as operações
            // agendadas (como o 'persist') são permanentemente salvas no banco de dados.
            em.getTransaction().commit();

            // Se qualquer tipo de erro (Exception) ocorrer durante o bloco 'try'...
        } catch (Exception e) {
            // ...primeiro, verifica se a transação ainda está ativa. Isso pode acontecer
            // se o erro ocorreu depois do 'begin()' mas antes do 'commit()'.
            if (em.getTransaction().isActive()) {
                // Se a transação estiver ativa, desfaz todas as alterações. Isso é chamado de 'rollback'.
                // Garante que o banco de dados não fique em um estado inconsistente (dados salvos pela metade).
                em.getTransaction().rollback();
            }
            // Imprime uma mensagem de erro no console, incluindo a causa do problema.
            // (Observação: a mensagem original mencionava "funcionário", corrigido para "consulta").
            System.err.println("Erro ao salvar a consulta: " + e.getMessage());

            // Relança a exceção. Isso permite que a camada que chamou este método (ex: a tela)
            // saiba que um erro ocorreu e possa tratá-lo também (ex: mostrar um JOptionPane).
            throw e;

            // O bloco 'finally' é sempre executado, tenha ocorrido um erro ou não.
        } finally {
            // Verifica se o EntityManager ainda está aberto.
            if (em.isOpen()) {
                // Se estiver aberto, ele é fechado para liberar a conexão com o banco de dados
                // e outros recursos, evitando vazamentos de memória.
                em.close();
            }
        }
    }

    public List<Consulta> buscarPorPetId(int petId) {
        EntityManager em = CheckPets.getEntityManager();
        try {
            // JPQL para selecionar Consultas (c) onde o id do Pet associado é igual ao parâmetro
            String jpql = "SELECT c FROM Consulta c WHERE c.pet.id = :idDoPet";

            TypedQuery<Consulta> query = em.createQuery(jpql, Consulta.class);
            query.setParameter("idDoPet", petId);

            return query.getResultList();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Consulta> buscarPorVeterinario(int veterinarioId) {
        EntityManager em = CheckPets.getEntityManager();

        try {
            // JPQL para selecionar Consultas (c) onde o id do Pet associado é igual ao parâmetro
            String jpql = "SELECT c FROM Consulta c WHERE c.veterinario.id = :idVeterinario";

            TypedQuery<Consulta> query = em.createQuery(jpql, Consulta.class);
            query.setParameter("idVeterinario", veterinarioId);

            return query.getResultList();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    // Dentro da sua classe ConsultaDao.java
    // ... seus outros métodos de busca (buscarPorPetId, buscarPorPetNome, etc.) ...
    /**
     * Busca TODAS as consultas cadastradas no banco de dados. Os resultados são
     * ordenados pela data da consulta, da mais recente para a mais antiga.
     *
     * @return Uma lista com todas as consultas.
     */
    public List<Consulta> buscarTodas() {
        EntityManager em = CheckPets.getEntityManager();
        try {
            // JPQL para selecionar todas as Consultas.
            // "ORDER BY c.data DESC" ordena os resultados pela data em ordem decrescente.
            String jpql = "SELECT c FROM Consulta c";

            TypedQuery<Consulta> query = em.createQuery(jpql, Consulta.class);

            return query.getResultList();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}
