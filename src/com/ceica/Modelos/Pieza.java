package com.ceica.Modelos;

import com.ceica.bbdd.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Pieza extends ModeloBase {
    private static int idPieza=0;
    private int id;
    private String nombre;
    private String color;
    private Double precio;
    private Categoria categoria;

    public Pieza(String nombre, String color, Double precio) {
        this.id=idPieza++;
        this.nombre = nombre;
        this.color = color;
        this.precio = precio;
    }

    public Pieza() {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


    public static int getIdPieza() {
        return idPieza;
    }

    public static void setIdPieza(int idPieza) {
        Pieza.idPieza = idPieza;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Pieza\n" +
                "{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                '}'+"\n";
    }
    public static List<Pieza> getPieza() {
        List<Pieza> piezaList = new ArrayList<>();
        Connection conn = Conexion.conectar();
        String sql = "SELECT P.idpiezas,P.nombre,P.color,P.precio,C.idcategoria,C.nombre_categoria " +
                " FROM proveedores.piezas as P inner join categoria as C on P.idcategoria=C.idcategoria;";
        try {
            Statement stm = conn.createStatement();
            ResultSet respuesta = stm.executeQuery(sql);
            while (respuesta.next()) {
                Pieza pieza = new Pieza();
                pieza.setId(respuesta.getInt("idpiezas"));
                pieza.setNombre(respuesta.getString("nombre"));
                pieza.setColor(respuesta.getString("color"));
                pieza.setPrecio(respuesta.getDouble("precio"));
                Categoria categoria1=new Categoria();
                categoria1.setId(respuesta.getInt("idcategoria"));
                categoria1.setNombre(respuesta.getString("nombre_categoria"));
                pieza.setCategoria(categoria1);
                piezaList.add(pieza);
            }
        } catch (SQLException ex) {
            return piezaList;
        }
        try {
            conn.close();
        } catch (SQLException e) {

        }

        return piezaList;
    }
    public static boolean insertar(Pieza pieza) {
        Connection conn = Conexion.conectar();
        String sql = "insert into pieza (nombre,color,precio,categoria,) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, pieza.getNombre());
            pst.setString(2, pieza.getColor());
            pst.setDouble(3, pieza.getPrecio());
            pst.setInt(4, pieza.categoria.getId());
            if (pst.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }
    public static boolean editarPrecioPieza(int idPieza, Double precio) {
        Connection conn = Conexion.conectar();
        String sql = "update pieza set nombre=? where idPieza=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, String.valueOf(precio));
            pst.setString(2, String.valueOf(idPieza));
            if (pst.executeUpdate() > 0) {
                conn.close();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return false;
        }

    }
    public static boolean eliminarPieza(String idPieza) {
        Connection conn = Conexion.conectar();
        String sql = "delete from pieza where idPieza=?";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, idPieza);
            if (pst.executeUpdate() > 0) {
                conn.close();
                return true;
            } else {
                conn.close();
                return false;
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return false;
        }

    }

    @Override
    public String getNombreTabla() {
        return "piezas";
    }
}
