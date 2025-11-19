package entities;

public class DispositivoIoT extends Base {
    private String serial;
    private String modelo;
    private String ubicacion;
    private String firmwareVersion;

    // Relación 1→1 unidireccional
    private ConfiguracionRed configuracionRed;

    // Constructor vacío (requerido)
    public DispositivoIoT() { 
    }

    // Constructor completo
    public DispositivoIoT(long id, boolean eliminado, String serial, String modelo,
                          String ubicacion, String firmwareVersion,
                          ConfiguracionRed configuracionRed) {

        super(id, eliminado);
        this.serial = serial;
        this.modelo = modelo;
        this.ubicacion = ubicacion;
        this.firmwareVersion = firmwareVersion;
        this.configuracionRed = configuracionRed;
    }
    
    // Constructor simple
    
    public DispositivoIoT(String serial, String modelo, String ubicacion, String firmwareVersion) {
    this.serial = serial;
    this.modelo = modelo;
    this.ubicacion = ubicacion;
    this.firmwareVersion = firmwareVersion;
    this.eliminado = false;   // por defecto
}

    // ---------------------------
    // Getters y Setters
    // ---------------------------

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public void setConfiguracionRed(ConfiguracionRed configuracionRed) {
        this.configuracionRed = configuracionRed;
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

    @Override
    public String toString() {
        return "DispositivoIoT { " +
                "id=" + id +
                ", eliminado=" + eliminado +
                ", serial='" + serial + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                ", configuracionRed=" + configuracionRed +
                " }";
    }
}
