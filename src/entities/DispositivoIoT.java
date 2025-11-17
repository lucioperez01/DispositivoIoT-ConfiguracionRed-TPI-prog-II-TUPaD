/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Usuario
 */
public class DispositivoIoT {
    private Long id;
    private boolean eliminado;
    private String serial, modelo, ubicacion, firmwareVersion;
    private ConfiguracionRed configuracionRed; // 1→1 unidireccional
    
    public DispositivoIoT() {}
    
    public DispositivoIoT(Long id, boolean eliminado, String serial, String modelo,
        String ubicacion, String firmwareVersion, ConfiguracionRed conf) {
        this.id = id;
        this.eliminado = eliminado;
        this.serial = serial;
        this.modelo = modelo;
        this.ubicacion = ubicacion;
        this.firmwareVersion = firmwareVersion;
        this.configuracionRed = conf;
    }
    
    public DispositivoIoT(String serial, String modelo, String ubicacion, String firmwareVersion) {
    this.serial = serial;
    this.modelo = modelo;
    this.ubicacion = ubicacion;
    this.firmwareVersion = firmwareVersion;
    this.eliminado = false;
}
    
    //-------------------------------
    // setters/getters

    public void setId(Long id) {
        if(id !=null) {
            this.id = id;
        }
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public void setSerial(String serial) {
        if(serial != null){
            this.serial = serial;
        }
    }

    public void setModelo(String modelo) {
        if(modelo != null) {
            this.modelo = modelo;
        }
    }

    public void setUbicacion(String ubicacion) {
        if(ubicacion != null) {
            this.ubicacion = ubicacion;    
        }
    }

    public void setFirmwareVersion(String firmwareVersion) {
        if(firmwareVersion != null) {
            this.firmwareVersion = firmwareVersion;
        }
    }

    public void setConfiguracionRed(ConfiguracionRed configuracionRed) {
        if(configuracionRed != null) {
            this.configuracionRed = configuracionRed;
        }
    }

    public Long getId() {
        return id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public String getSerial() {
        return serial;
    }

    public String getModelo() {
        return modelo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public ConfiguracionRed getConfiguracionRed() {
        return configuracionRed;
    }
    
    
    //toString

    @Override
    public String toString() {
        return "DispositivoIoT{" + "id=" + id + ", eliminado=" + eliminado + ", serial=" + serial + ", modelo=" + modelo + ", ubicacion=" + ubicacion + ", firmwareVersion=" + firmwareVersion + ", configuracionRed=" + configuracionRed + '}';
    }
    
    
    
}
