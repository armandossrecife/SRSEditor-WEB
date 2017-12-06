package br.ufpi.dc.dao;

import br.ufpi.dc.controle.importar.Importador;
import br.ufpi.dc.tools.Constante;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author rafael
 */
public class CriarBanco {

    private String driver = "com.mysql.jdbc.Driver";
    //verificar se precisar modificar isso das barras
    private String url = "jdbc:mysql://localhost";
    private String login = "editor";
    private String pass = "editor";
    private Connection con = null;
    private Statement s;

    /**
     * Faz a conexao e retorna a conexao com banco de dados
     *
     * @return Connetion Retorna a conexao com banco
     * @throws ConeException
     */
    public void criarBanco() throws IOException {
        try {
            Class.forName(driver);

            con = DriverManager.getConnection(url, login, pass);
            DatabaseMetaData meta = con.getMetaData();
            ResultSet rs = meta.getCatalogs();
            List<String> listaBancos = new ArrayList<String>();

            while (rs.next()) {
                String listofDatabases = rs.getString("TABLE_CAT");
                listaBancos.add(listofDatabases);
            }

            if (!listaBancos.contains(Constante.BANCOMYSQL)) {
                s = con.createStatement();
                s.executeUpdate("CREATE DATABASE  IF NOT EXISTS `" + Constante.BANCOMYSQL + "`");
            }
            br.ufpi.dc.controle.importar.Importador importador = new Importador();
            importador.importarTabelasIniciais();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        try {
            con.close();
        } catch (Exception e) {
        }
    }
}
