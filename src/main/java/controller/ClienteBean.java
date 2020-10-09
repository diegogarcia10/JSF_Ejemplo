package controller;

import dao.ClienteDAO;
import modelo.Cliente;
import sun.rmi.runtime.Log;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@ManagedBean(name="clienteBean")
@RequestScoped
public class ClienteBean {
    public List<Cliente> obtenerClientes(){
        /*
        List<Cliente> listaclientes=new ArrayList<>();
        Cliente c1= new Cliente();
        c1.setId(1L);
        c1.setNombres("Diego");
        c1.setApellidos("Ochoa");
        c1.setDireccion("San Salvador");
        c1.setEmail("ddjochoa10@gmail.com");
        listaclientes.add(c1);
        listaclientes.add(c1);
        listaclientes.add(c1);
        return listaclientes;*/
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

        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
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
        System.out.println("Cliente eliminado..");
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
