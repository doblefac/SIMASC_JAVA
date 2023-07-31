package com.ccb.simasc.comun.conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ccb.simasc.comun.util.Constantes;
import java.sql.SQLException;

public class ConexionDB {
    private static final Logger logger = LogManager.getLogger(ConexionDB.class.getName());

    private static Connection con;

    public ConexionDB() {
    }

    public Connection getConexionDB() throws SQLException {
    	
    	// Definicion variables
    	InitialContext context= null;
        DataSource dataSource = null;
        Connection conexion = null;
        
        try {
        	context = new InitialContext();
            dataSource =(DataSource)context.lookup(Constantes.db);
            conexion = (Connection)dataSource.getConnection();
            //context.close();
		} catch (NamingException e) {
            logger.error("ERROR ConnectionSingle" + e.getMessage());
		}
        
        return conexion;
    }

    
    public void cerrarCursores(Connection conn,Statement pstatement, ResultSet rs) {
    	try {
    		if (rs != null)
                rs.close();           
            if (pstatement != null)
                pstatement.close();           
            if (conn != null)
                conn.close();
           
        } catch (Exception e2) {
        	logger.error("Error cerrando Conexion AW:" + e2.getMessage());
        	logger.error("Error ->" + e2);
        }
    }	

    public void closeConnection(Statement stmt, ResultSet rs, PreparedStatement pst) throws SQLException {
        if (rs != null) {
            rs.close();            
        }
        if (stmt != null) {
            stmt.close();            
        }
        if (pst != null) {
            pst.close();
        }
        if (con != null) {
            con.close();
            con = null;
        }
    }
}
