package com.ceica.Controladores;

import com.ceica.Modelos.Usuario;

public class LoginController {
    public static boolean login(String usuario,String password){

        if(Usuario.getUsuario(usuario,password)){
            return true;

        }else{
            return false;
        }
    }
}
