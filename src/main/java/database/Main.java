package database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    /*
    1. Otwórz nowy projekt mavenowy  oraz wrzuć go na gita
    2. Utwórz klasę która połączy się z Twoją bazą danych oraz wrzuć na gita
    3. Wykonaj zapytanie które wyświetli wszystkie rekordy z tabeli employees oraz wrzuć na gita
    4. Utwórz metodę, która będzie dodawała nowe wpisy do bazy danych oraz wrzuć na gita
    5. Utwórz metodę, która będzie aktualizowałą wpisy w bazie danych oraz wrzuć na gita
    6. Utwórz metodę, która będzie usuwała wpisy z bazy danych
    7. Napisz tranzakcję dla metod typu CRUD oraz wrzuć na gita
    8. Napisz testy jednostkowe dla swojej aplikacji oraz wrzuć na gita
    9. Wykorzystaj projekt, w którym łączyliśmy się z API i pobieraliśmy dane o pracownikach
    i zrób metodę, która doda ich do bazy danych a brakujące dane wygeneruje lub będzie ustawiać domyślną wartość

    */
    public static void main(String[] args) {
        Connection connection = new SQLConnection().getConnection();
        CRUDStatementSQL crudStatementSQL = new CRUDStatementSQL(connection);

        String tableName = "Employees";
        String insertQuery = "Insert INTO Employees (LastName, FirstName, Address, City, Salary, Age, StartJobDate, Benefit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String updateQuery = "UPDATE Employees SET LastName=?, FirstName=?, Address=?, City=?, Salary=?, Age=?, StartJobDate=?, Benefit=? WHERE ID=?";

   //     crudStatementSQL.selectSQLbyJDBC(tableName);

        List<String> columnNames = new ArrayList<>();
        columnNames.add("FirstName");
        columnNames.add("LastName");

      //  crudStatementSQL.selectSQLbyJDBC(columnNames, tableName);


        TransactionsSQL.transactions(connection, crudStatementSQL.insertSQLbyJDBC(insertQuery));
     //   TransactionsSQL.transactions(connection, crudStatementSQL.updateSQLbyJDBC(updateQuery));
    //    TransactionsSQL.transactions(connection,  crudStatementSQL.deleteSQLbyID(8));



        Map<String, String> updateValues = new HashMap<>();
        updateValues.put("LastName","Jasiek");
        updateValues.put("Age","15");


        TransactionsSQL.transactions(connection, crudStatementSQL.updateSQLbyJDBC(updateValues, tableName, "where LastName = 'koro'"));
        TransactionsSQL.transactions(connection, crudStatementSQL.updateSQLbyJDBC(updateValues, tableName, "where LastName = 'asd' or 1 = 1"));

       //   TransactionsSQL.transactions(connection, crudStatementSQL.deleteSQLbyID(tableName, "id","2"));


    }
}
