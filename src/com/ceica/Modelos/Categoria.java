package com.ceica.Modelos;

import com.ceica.bbdd.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private int id;
    private String nombre;

    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Categoria() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return id +" -> " + nombre + "\n";
    }
    public static List<Categoria> getCategorias(){
        List<Categoria> categoriaList = new ArrayList<>();
        Connection conn = Conexion.conectar();
        String sql = "select * from categoria";
        try {
            Statement stm = conn.createStatement();
            ResultSet respuesta = stm.executeQuery(sql);
            while (respuesta.next()) {
                Categoria categoria= new Categoria();
                categoria.setId(respuesta.getInt("idcategoria"));
                categoria.setNombre(respuesta.getString("nombre_categoria"));

                categoriaList.add(categoria);
            }
        } catch (SQLException ex) {
            return categoriaList;
        }
        try {
            conn.close();
        } catch (SQLException e) {

        }

        return categoriaList;
    }
}
