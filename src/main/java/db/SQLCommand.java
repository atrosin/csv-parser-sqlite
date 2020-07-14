package db;

import java.sql.SQLException;
import java.sql.Statement;

public class SQLCommand {
    private Statement statement;

    public SQLCommand(Statement statement) {
        this.statement = statement;
    }

    public void insertIntoDataTable(String[] item){
        String insertSql = "INSERT INTO DATA (A,B,C,D,E,F,G,H,I,J) " +
                "VALUES (" +
                "'" + item[0] + "'," +
                "'" + item[1] + "'," +
                "'" + item[2] + "'," +
                "'" + item[3] + "'," +
                "'" + item[4] + "'," +
                "'" + item[5] + "'," +
                "'" + item[6] + "'," +
                "'" + item[7] + "'," +
                "'" + item[8] + "'," +
                "'" + item[9] + "'" +
                ");";
        execute(insertSql);
    }

    public void createDataTable(){
        String createTable = "CREATE TABLE DATA " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " A           TEXT    NOT NULL, " +
                " B           TEXT    NOT NULL, " +
                " C           TEXT    NOT NULL, " +
                " D           TEXT    NOT NULL, " +
                " E           TEXT    NOT NULL, " +
                " F           TEXT    NOT NULL, " +
                " G           TEXT    NOT NULL, " +
                " H           TEXT    NOT NULL, " +
                " I           TEXT    NOT NULL, " +
                " J           TEXT    NOT NULL)";
        execute(createTable);
    }

    private void execute(String query){
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}