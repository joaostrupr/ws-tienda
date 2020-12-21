/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Orden;
import facades.ClienteFacade;
import facades.OrdenFacade;
import facades.ProductoFacade;
import java.util.Date;
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
@WebService(serviceName = "Ordenes")
public class OrdenServiceImpl {

    @EJB
    private OrdenFacade ordenFacade;
    @EJB
    private ClienteFacade clienteFacade;
    @EJB
    private ProductoFacade productoFacade;

    @WebMethod(operationName = "eliminarOrden")
    @WebResult(name = "respuesta")
    public boolean eliminarOrden(Orden orden) {
        try {
            ordenFacade.remove(orden);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @WebMethod(operationName = "actualizarOrden")
    @WebResult(name = "respuesta")
    public boolean actualizarOrden(Integer idOrden, Integer idCliente, Integer idProducto, Integer cantidad) {
        try {
            Orden orden = ordenFacade.find(idOrden);
            orden.setCliente(clienteFacade.find(idCliente));
            orden.setProducto(productoFacade.find(idProducto));
            orden.setCantidad(cantidad);
            ordenFacade.edit(orden);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @WebMethod(operationName = "listarOrdenes")
    @WebResult(name = "clientes")
    public List<Orden> listarOrdenes() {
        return ordenFacade.findAll();
    }

    @WebMethod(operationName = "agregarOrden")
    @WebResult(name = "respuesta")
    public boolean agregarOrden(Integer idCliente, Integer idProducto, Integer cantidad) {
        try {
            Orden orden = new Orden();
            orden.setId(ordenFacade.id() + 1);
            orden.setCliente(clienteFacade.find(idCliente));
            orden.setProducto(productoFacade.find(idProducto));
            orden.setCantidad(cantidad);
            orden.setFecha(new Date());
            ordenFacade.create(orden);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
