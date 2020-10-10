package controller;

import dao.ClienteDAO;
import dao.UsuarioDAO;
import modelo.Cliente;
import modelo.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean {
    public List<Usuario> obtenerUsuarios(){
        UsuarioDAO usuarioDAO= new UsuarioDAO();
        return usuarioDAO.obtenerUsarios();
    }

}
