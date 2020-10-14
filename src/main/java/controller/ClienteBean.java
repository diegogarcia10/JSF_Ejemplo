package controller;

import dao.ClienteDAO;
import modelo.Cliente;


import javax.enterprise.context.SessionScoped;


import javax.faces.annotation.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



@ManagedBean(name = "clienteBean")
@RequestScoped
public class ClienteBean{

    public List<Cliente> obtenerClientes(){

        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.obtenerClientes();
    }

    public String editar(Long id){
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente c = new Cliente();
        c = clienteDAO.buscar(id);
        System.out.println("******************************************");
        System.out.println("Objeto encontrado: "+c);

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("cliente", c);
        return "/faces/editar.xhtml";
    }
    public String actualizar(Cliente cliente) {
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
        cliente.setFactualizar(date);

        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.editar(cliente);
        return "/faces/index.xhtml";
    }
    // eliminar un cliente
    public String eliminar(Long id) {
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.eliminar(id);
        System.out.println("Cliente eliminado");
        return "/faces/index.xhtml";
    }
    public String nuevo() {
        Cliente c= new Cliente();
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("cliente", c);
        return  "/faces/nuevo.xhtml";
    }

    public String guardar (Cliente cliente) {
        //guarda la fecha de registro
        Date fechaActual= new Date();
        cliente.setFregistro(new java.sql.Date(fechaActual.getTime()));
        ClienteDAO clienteDAO= new ClienteDAO();
        clienteDAO.guardar(cliente);
        return  "/faces/index.xhtml";
    }

}
