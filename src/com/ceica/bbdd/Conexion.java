package com.ceica.bbdd;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conexion {
public static Connection conectar() {
    Properties propiedades = new Properties();
    try (FileInputStream entrada = new FileInputStream("config.properties")) {
        propiedades.load(entrada);
        String url=propiedades.getProperty("db.url");
        String usuario= propiedades.getProperty("db.usuario");
        String password= propiedades.getProperty("db.password");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion= DriverManager.getConnection(url,usuario,password);
        return conexion;



    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
}
