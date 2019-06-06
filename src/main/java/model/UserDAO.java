package model;

import entidade.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("WebApplication4PU");
    private EntityManager entityManager;

    public UserDAO() {
        this.entityManager = emf.createEntityManager();
    }

    public Usuario retornarPorId(Long id) {
        String sql = "FROM " + Usuario.class.getName() + " WHERE id = :id";
        Query query = this.entityManager.createQuery(sql);
        query.setParameter("id", id);

        return (Usuario) query.getSingleResult();
    }
}