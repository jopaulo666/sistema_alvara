/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sistemaos.dao;

import java.sql.*;

/**
 * 
 * @author João Paulo Mendes <jp_cbc@hotmail.com>
 */
public class ModuloConexao {
    public static Connection conector(){
        java.sql.Connection conexao = null;
        //chama o driver
        String driver = "com.mysql.jdbc.Driver";
        
        // informções referentes ao banco de dados
        String url = "jdbc:mysql://localhost:3306/sistema_os"; // no desktop, sempre colocar jdbc antes de qq concexão
        String user = "root";
        String password = "";
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            System.out.println("Erro ao conecta-se ao banco de dados: " + e.getMessage());
            return null;
        }
    }
}
