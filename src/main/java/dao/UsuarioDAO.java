package dao;

import modelo.Cliente;
import modelo.JPAUtil;
import modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class UsuarioDAO {
    EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

    // guardar usuario
    public void guardar(Usuario usuario) {
        entity.getTransaction().begin();
        entity.persist(usuario);
        entity.getTransaction().commit();
    }


    // editar usuario
    public void editar(Usuario usuario) {
        entity.getTransaction().begin();
        entity.merge(usuario);
        entity.getTransaction().commit();

    }

    // buscar cliente
    public Usuario buscar(Long id) {
        Usuario c = new Usuario();
        c = entity.find(Usuario.class, id);
        return c;
    }

    /// eliminar cliente
    public void eliminar(Long id) {
        Usuario c = new Usuario();
        c = entity.find(Usuario.class, id);
        entity.getTransaction().begin();
        entity.remove(c);
        entity.getTransaction().commit();
    }

    public List<Usuario> obtenerUsarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        Query q = entity.createQuery("SELECT u FROM Usuario  u");
        listaUsuarios= q.getResultList();
        System.out.print(listaUsuarios.toString());
        return listaUsuarios;
    }
}
