package controller;

import dao.ClienteDAO;
import dao.UsuarioDAO;
import modelo.Cliente;
import modelo.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean {

    public List<Usuario> obtenerUsuarios(){
        UsuarioDAO usuarioDAO= new UsuarioDAO();
        return usuarioDAO.obtenerUsarios();
    }

    public String editar(Long id){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario c = new Usuario();
        c = usuarioDAO.buscar(id);
        System.out.println("******************************************");
        System.out.println("Objeto encontrado: "+c);

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("usuario", c);
        return "/faces/usuarios/editar.xhtml";
    }

    // eliminar un cliente
    public String eliminar(Long id) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.eliminar(id);
        System.out.println("Usuario eliminado");
        return "/faces/usuarios/index.xhtml";
    }
    public void nuevo() throws IOException {
        Usuario c= new Usuario();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("usuario", c);
        ExternalContext context= FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("nuevo.xhtml");

    }

    public void guardar (Usuario usuario) throws IOException {
        //guarda la fecha de registro
        Date fechaActual= new Date();
        usuario.setFechaRegistro(new java.sql.Date(fechaActual.getTime()));
        UsuarioDAO usuarioDAO= new UsuarioDAO();
        usuarioDAO.guardar(usuario);
        ExternalContext context= FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("index.xhtml");
    }

    public String actualizar(Usuario usuario) {
        //guarda la fecha de actualizacion
        SimpleDateFormat isoFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date=new Date();
        System.out.print(date);
        try {
            date = isoFormat.parse(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        usuario.setFechaActualizar(date);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.editar(usuario);
        return "/faces/usuarios/index.xhtml";
    }

}
