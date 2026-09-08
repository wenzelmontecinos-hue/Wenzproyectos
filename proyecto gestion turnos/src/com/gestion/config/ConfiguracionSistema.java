package com.gestion.config;

public class ConfiguracionSistema {
    private static ConfiguracionSistema instancia;
    private String nombreSistema;

    private ConfiguracionSistema() {
        this.nombreSistema = "Sistema Multi-Dominio de Gestión de Recursos y Turnos";
    }

    public static synchronized ConfiguracionSistema getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionSistema();
        }
        return instancia;
    }

    public String getNombreSistema() { return nombreSistema; }
}