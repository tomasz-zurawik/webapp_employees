package database;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class CRUDStatementSQL {

    private Connection conn;

    public CRUDStatementSQL(Connection conn) {
        this.conn = conn;
    }

    public void selectSQLbyJDBC(String tableName) {
        try {
            Statement statement = conn.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * from ").append(tableName);
            ResultSet rs = statement.executeQuery(sb.toString());
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);

                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void selectSQLbyJDBC(List<String> columnNames, String tableName) {
        try {
            Statement statement = conn.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            for (String columnName : columnNames) {
                if (columnNames.get(columnNames.size() - 1).equals(columnName))
                    sb.append(columnName);
                else {
                    sb.append(columnName).append(", ");
                }

            }
            sb.append(" from ").append(tableName);
            ResultSet rs = statement.executeQuery(sb.toString());
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);

                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement insertSQLbyJDBC(String insertQuery) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insertQuery);
            ps.setString(1, "Jarek");
            ps.setString(2, "Garek");
            ps.setString(3, "Bursztynowa");
            ps.setString(4, "Wilno");
            ps.setInt(5, 4500);
            ps.setInt(6, 42);
            ps.setDate(7, Date.valueOf("2018-10-15"));
            ps.setInt(8, 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ps;
    }

    public String updateSQLbyJDBC(Map<String, String> updateQuery, String tableName, String whereCondition) {

        if(checkWhereCondition(whereCondition)) {
            System.out.println("Błędne zapytanie");
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(tableName);
        sb.append(" SET ");

        int j = updateQuery.entrySet().size() - 1;
        int i = 0;

        for (Map.Entry<String, String> entry : updateQuery.entrySet()) {
            if (i < j) {
                sb.append(entry.getKey() + " = " + " '" + entry.getValue() + "', ");
                i++;
            } else {
                sb.append(entry.getKey() + " = " + "'" + entry.getValue() + "' ");
            }
        }
        sb.append(whereCondition);

        return sb.toString();
    }

    public String deleteSQLbyID(String tableName, String columnName, String value) {

        if(checkWhereCondition(value)) {
            System.out.println("Błędne zapytanie");
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(tableName);
        sb.append(" WHERE ");
        sb.append(columnName);
        sb.append(" = ");
        sb.append(value);

        return sb.toString();
    }

    public boolean checkWhereCondition(String whereCondition) {
        return (whereCondition.toUpperCase().indexOf(" OR ") > 0 || whereCondition.toUpperCase().indexOf(" AND ") > 0) ? Boolean.TRUE : Boolean.FALSE;
    }

}
