
package entities;

public abstract class Base {
    Long id;
    boolean eliminado;
    
    public Base(){
    };
    
    protected Base(Long id, boolean eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public boolean isEliminado() {
        return eliminado;
    }
    
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
}
