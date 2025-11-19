/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Usuario
 */
public class ConfiguracionRed extends Base{
    private String ip, mascara, gateway, dnsPrimario;
    private boolean dhcpHabilitado;
    
    public ConfiguracionRed() {};
    
    // Constructor completo
    public ConfiguracionRed(Long id, boolean eliminado, String ip, String mascara, String gateway,
    String dnsPrimario, boolean dhcpHabilitado) {
        super(id, eliminado);
        this.ip = ip;
        this.mascara = mascara;
        this.gateway = gateway;
        this.dnsPrimario = dnsPrimario;
        this.dhcpHabilitado = dhcpHabilitado;
    }
    
    // Constructor simple
    public ConfiguracionRed(String ip, String mascara, String gateway,
                        String dnsPrimario, boolean dhcpHabilitado) {
    this.ip = ip;
    this.mascara = mascara;
    this.gateway = gateway;
    this.dnsPrimario = dnsPrimario;
    this.dhcpHabilitado = dhcpHabilitado;
    this.eliminado = false; // por defecto
}

    
    //-------------------------------
    //setters/getters
    
    public void setId(Long id) {
        if (id != null) {
            this.id = id;    
        }
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public void setIp(String ip) {
        if(ip != null) {
            this.ip = ip;    
        }
    }

    public void setMascara(String mascara) {
        if(mascara != null) {
            this.mascara = mascara;
        }
    }

    public void setGateway(String gateway) {
        if(gateway != null) {
            this.gateway = gateway;
        }
    }

    public void setDnsPrimario(String dnsPrimario) {
        if(dnsPrimario != null) {
            this.dnsPrimario = dnsPrimario;    
        }
    }

    public void setDhcpHabilitado(boolean dhcpHabilitado) {
        this.dhcpHabilitado = dhcpHabilitado;
    }

    public Long getId() {
        return id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public String getIp() {
        return ip;
    }

    public String getMascara() {
        return mascara;
    }

    public String getGateway() {
        return gateway;
    }

    public String getDnsPrimario() {
        return dnsPrimario;
    }

    public boolean isDhcpHabilitado() {
        return dhcpHabilitado;
    }
    
    
    //toString

    @Override
    public String toString() {
        return "ConfiguracionRed{" + "id=" + id + ", eliminado=" + eliminado + ", ip=" + ip + ", mascara=" + mascara + ", gateway=" + gateway + ", dnsPrimario=" + dnsPrimario + ", dhcpHabilitado=" + dhcpHabilitado + '}';
    }
    
}
