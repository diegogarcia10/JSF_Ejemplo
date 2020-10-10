package dao;

import modelo.Cliente;
import modelo.JPAUtil;
import modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
    public List<Usuario> obtenerUsarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        Query q = entity.createQuery("SELECT u FROM Usuario  u");
        listaUsuarios= q.getResultList();
        System.out.print(listaUsuarios.toString());
        return listaUsuarios;
    }
}
