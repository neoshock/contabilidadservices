package com.global.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    private String user = "postgres", password = "123456", database = "erp_global";
    private Connection connection;
    private Statement statement;
    private ResultSet result = null;
    
    public Conexion() {
        
    }
    
    /*Tenemos este construtor donde solo le pasamos el nombre
        de la base de datos que se quiere conectar y los demas
        valores requeridos estas definidos por defecto
    */
    public Conexion(String database) {
        this.database = database;
    }

    /*El siguiente constructor es en la caso que se requiera 
        conectar a una base de datos con un usuario especifico
        y no con el predefinido
    */
    public Conexion(String database, String user, String password) {
        this.database = database;
        this.user = user;
        this.password = password;
    }
    
    /* La función conectar() es la encargada de hacer la
        conexion la base de datos de acuerdo a los 
        parametros que se hayan pasado en el constructor
    */
    public boolean conectar() {
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/" + database, user, password
                );
                statement = connection.createStatement();
                System.out.println("Conexion exitosa");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("No hay conexion a la base de datos: " + e.getMessage());
        }
        return false;
    }
    
    /*La función desconectar() cierra la la conexión con
        la base de datos
    */
    public boolean desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                statement.close();
                System.out.println("Conexion desconectada");
                return true;
            } else {
                System.out.println("No hay una conexion para desconectar");
            }
        } catch (SQLException ex) {
            System.out.println("Hubo un problema al desconectar la conexion");
        }
        return false;
    }
    
    /*La función consultar(sql) ejecuta una sentencia sql que
        pasemos por parametro y retorna un ResultSet que sera manipulado
        en un DAO en especifico
    */
    public ResultSet consultar(String sql) {
        try {
            conectar();
            result = statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Error: No se ejecuto la consulta: " + ex.getMessage());
        }
        return result;
    }
    
    public ResultSet ejecutar(String sql) {
        try {
            conectar();
            result = statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Error: No se ejecuto la consulta: " + ex.getMessage());
        }
        return result;
    }
    
    /*La funcion insertar(sql) ejecuta una sentencia de crear
        un registro en la base de datos, retorna el número
        de registro ingresados en la base de datos
    */
    public int insertar(String sql) {
        int result = -1;
        try {
            conectar();
            result = statement.executeUpdate(sql);
            System.out.println("Datos ingresados correctamente");
        } catch (SQLException e) {
            System.out.println("Error al insertar datos: " + e.getMessage());
        } finally {
            desconectar();
        }
        return result;
    }
    
    /*La función modificar(sql) ejecuta una sentencia de
        modificar en la base de datos y retorna el
        número de registros modificados en la base de datos
    */
    public int modificar(String sql) {
        int result = -1;
        try {
            conectar();
            result = statement.executeUpdate(sql);
            System.out.println("Datos modificados correctamente");
        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar los datos: " + e.getMessage());
        } finally {
            desconectar();
        }
        return result;
    }
    
    /* La función eliminar(sql) ejecuta una sentencia de
        eliminar un registro de la base de datos y retorna
        el número de registros eliminados en la base de datos
    */
    public int eliminar(String sql) {
        int result = -1;
        try {
            conectar();
            result = statement.executeUpdate(sql);
            System.out.println("Se ha eliminado el registro");
        } catch (SQLException e) {
            System.out.println("No se ha podido eliminar el registro " + e.getMessage());
        } finally {
            desconectar();
        }
        return result;
    }
    
    /*Apartir de aqui se presentan los Getters & Setters
        de las propiedades privadas de esta clase*/
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getResult() {
        return result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }
}