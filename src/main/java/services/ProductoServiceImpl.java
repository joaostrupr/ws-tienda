/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Producto;
import facades.ProductoFacade;
import java.math.BigDecimal;
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
@WebService(serviceName = "Productos")
public class ProductoServiceImpl {

    @EJB
    private ProductoFacade productoFacade;

    @WebMethod(operationName = "buscarProducto")
    @WebResult(name = "producto")
    public Producto buscarProducto(Integer idProducto) {
        return productoFacade.find(idProducto);
    }

    @WebMethod(operationName = "eliminarProducto")
    @WebResult(name = "respuesta")
    public boolean eliminarProducto(Producto producto) {
        try {
            productoFacade.remove(producto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @WebMethod(operationName = "actualizarProducto")
    @WebResult(name = "respuesta")
    public boolean actualizarProducto(Producto producto) {
        productoFacade.edit(producto);
        return true;
    }

    @WebMethod(operationName = "listarProductos")
    @WebResult(name = "productos")
    public List<Producto> listarProductos() {
        return productoFacade.findAll();
    }

    @WebMethod(operationName = "agregarProducto")
    @WebResult(name = "respuesta")
    public boolean agregarProducto(String nombre, String descripcion, BigDecimal precio) {
        try {
            Producto producto = new Producto();
            producto.setId(productoFacade.id() + 1);
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            productoFacade.create(producto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
