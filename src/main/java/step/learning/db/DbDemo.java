package step.learning.db;
import com.google.inject.Inject;
import step.learning.services.rnd.CodeGen;

import java.sql.*;
import java.util.Random;

public class DbDemo {


/*    private Connection connection;
    public Connection getConnection() {
        if (connection == null){
            Driver mySqlDriver = null;
            try {
                mySqlDriver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(mySqlDriver);
                String connectionString = "jdbc:mysql://localhost:3306/java-pv-111" +
                        "?useUnicode=true&characterEncoding=UTF-8";
                connection = DriverManager.getConnection(connectionString);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
        return connection;
    }*/
    private final Connection connection;
//    private final CodeGen codeGen;
    private final Random random;

    @Inject
    public DbDemo(Connection connection, CodeGen codeGen, Random random) {
        this.connection = connection;
//        this.codeGen = codeGen;
        this.random = random;
    }

    private void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS randoms (" +
                "id BIGINT UNSIGNED PRIMARY KEY DEFAULT (UUID_SHORT())," +
                "rand_int INT," +
                "rand_str VARCHAR(64)" +
                ") ENGINE = InnoDB, DEFAULT CHARSET = utf8mb4";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Create OK");
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    private void printRandoms(){
        String sql = "SELECT * FROM randoms";
        try( Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(sql);
            while (res.next()){
                System.out.printf("%d %d %s\n",
                        res.getLong("id"),
                        res.getInt("rand_int"),
                        res.getString("rand_str"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    private void addRandom(){
        String sql = "INSERT INTO randoms(rand_int, rand_str) VALUES (?, ?)";
        try (PreparedStatement prep = connection.prepareStatement( sql )){
            prep.setInt(1,random.nextInt());
            prep.setString(2,/*codeGen.newCode(10)*/ "abcdefghij");
            prep.executeUpdate();
            System.out.println("Insert Ok!");
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }


    public void run(){
        if (connection != null){
            System.out.println("Connection OK");
//            createTable();
            addRandom();
            printRandoms();
        }
    }
}
