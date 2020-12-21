/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cliente;
import facades.ClienteFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 *
 * @author Joao
 */
@Stateless
@WebService(serviceName = "Clientes")
public class ClienteServiceImpl {

    @EJB
    private ClienteFacade clienteFacade;

    @WebMethod(operationName = "buscarCliente")
    @WebResult(name = "cliente")
    public Cliente buscarCliente(Integer idCliente) {
        try {
            return clienteFacade.find(idCliente);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod(operationName = "agregarCliente")
    @WebResult(name = "respuesta")
    public boolean agregarCliente(String nombres, String apellidos) {
        try {
            Cliente cliente = new Cliente();
            cliente.setId(clienteFacade.id() + 1);
            cliente.setNombres(nombres);
            cliente.setApellidos(apellidos);
            clienteFacade.create(cliente);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @WebMethod(operationName = "eliminarCliente")
    @WebResult(name = "respuesta")
    public boolean eliminarCliente(Cliente cliente) {
        try {
            clienteFacade.remove(cliente);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @WebMethod(operationName = "actualizarCliente")
    @WebResult(name = "respuesta")
    public boolean actualizarCliente(Cliente cliente) {
        try {
            clienteFacade.edit(cliente);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @WebMethod(operationName = "listarClientes")
    @WebResult(name = "clientes")
    public List<Cliente> listarClientes() {
        try {
            return clienteFacade.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
