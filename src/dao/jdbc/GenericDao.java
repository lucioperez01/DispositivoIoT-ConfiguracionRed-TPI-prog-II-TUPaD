package dao.jdbc;

import com.sun.jdi.connect.spi.Connection;
import java.util.List;

public interface GenericDao<T> {
 
    T crear(T t) throws Exception;
    
    T leer(long id) throws Exception;
    
    
    List<T> leerTodos() throws Exception;
    
    void actualizar(T t) throws Exception;
    
    void eliminar(long id) throws Exception; 
    
    // variantes transaccionales
    T crear(Connection c, T t) throws Exception;
    void actualizar(Connection c, T t) throws Exception;
    void eliminar(Connection c, long id) throws Exception;
    T leer(Connection c, long id) throws Exception;

}
