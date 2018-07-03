package JDBCExample;

import org.python.util.PythonInterpreter;
import org.python.core.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.*;

public class Main {

    static final String JDBC_DRIVER = "com.splicemachine.db.jdbc.ClientDriver";
    static final String DB_URL = "jdbc:splice://localhost:1527/splicedb";

    static final String USER = "splice";
    static final String PASS = "admin";

    public static String PYTHON_SCRIPT =
            "from com.ziclix.python.sql import zxJDBC\n" +
                    "jdbc_url = \"jdbc:splice://localhost:1527/splicedb\"\n" +
                    "zxJDBC.autocommit = 1\n" +
                    "username = \"splice\"\n" +
                    "password = \"admin\"\n" +
                    "driver = \"com.splicemachine.db.jdbc.ClientDriver\"\n" +
                    "\n" +
                    "# obtain a connection using the with-statment\n" +
                    "def run():\n" +
                    "\tconn = zxJDBC.connect(jdbc_url, username, password, driver)\n" +
                    "\tc = conn.cursor()\n" +
                    "\tc.execute(\"select alias, javaclassname from sys.sysaliases {limit 3}\")\n" +
                    "\td = c.description\n" +
                    "\tresult = c.fetchall()\n" +
                    "\tconn.commit()\n" +
                    "\tc.close()\n" +
                    "\tconn.close()\n" +
                    "\treturn [[d,result]]";

    public static void main(String[] args) {
//        String str = new String("a java string");
//        int num = 1;
//        Character a = 'a';
//        String b = "abasdf";
//
//        List<String> re1 = new ArrayList<>();
//        List<String> re2 = new ArrayList<>();
//        jyTest(str, num, re1, re2);
//        genCodeTest(1, "abc");
//        passByReferenceTest();
//        callPythonGetProcedure();
//        callJavaGetProcedure();
//        call_GET_EMPLOYEE_MULTIPLE_OUTPUT_PARAMS();
//        call_GET_EMPLOYEE();
//        getTestTableRow();
        multipleRestulSetsTest();
        return;
    }


    // Testing whether the java object is passed in by reference or by value
    static void passByReferenceTest(){
        String[] strList = new String[5];
        Object[] args = new Object[1];
        args[0] = strList;

        String pythonScript = "from java.lang import String\n" +
                "# obtain a connection using the with-statment\n" +
                "def run(*args):\n" +
                "    strList = args[0]\n" +
                "    print type(strList)\n" +
                "    print type(args[0])\n" +
                "    strList[0] = String(\"abcd\")\n" +
                "    return";
        System.out.println("Execute Python script:");
        System.out.println(pythonScript);
        String funcName = "run";

        PythonInterpreter interpreter = new PythonInterpreter();

        try {
            interpreter.exec(pythonScript);
            PyFunction userFunc = interpreter.get(funcName, PyFunction.class);
            userFunc._jcall(args);
            return;
        }catch (Exception e){
            e.printStackTrace();
        }
        return;

    }

    static void genCodeTest(Object... args){
        return;
    }

    static void clazzTest(Object...args){
        Class clazz = args[0].getClass();
        String clazzName = clazz.getName();
        System.out.println(clazzName);
    }

    static ArrayList<String> jyTest3(){
        // The pythonScript takes in an argument as a column name of the sys.sysaliases tables
        //
        String pythonScript =
                "from com.ziclix.python.sql import zxJDBC\n" +
                        "jdbc_url = \"jdbc:splice://localhost:1527/splicedb\"\n" +
                        "zxJDBC.autocommit = 1\n" +
                        "username = \"splice\"\n" +
                        "password = \"admin\"\n" +
                        "driver = \"com.splicemachine.db.jdbc.ClientDriver\"\n" +
                        "\n" +
                        "# obtain a connection using the with-statment\n" +
                        "def run(col_name):\n" +
                        "    conn = zxJDBC.connect(jdbc_url, username, password, driver)\n" +
                        "    c = conn.cursor()\n" +
                        "    c.execute(\"select \"+col_name+\" from sys.sysaliases {limit 3}\")\n" +
                        "    d = c.description\n" +
                        "    result = c.fetchall()\n" +
                        "    conn.commit()\n" +
                        "    c.close()\n" +
                        "    conn.close()\n" +
                        "    return (c,d,result)";
        System.out.println("Execute Python script:");
        System.out.println(pythonScript);
        String funcName = "run";

        PythonInterpreter interpreter = new PythonInterpreter();

        try {
            interpreter.exec(pythonScript);
            PyFunction userFunc = interpreter.get(funcName, PyFunction.class);
            PyObject result = userFunc.__call__(new PyString("alias"));
            return null;
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    static void jyTest2(){
        ResultSet[] re = new ResultSet[1];
        String col_name = "GET_JAVA";
        Object[] args = {col_name, re};
        String pythonScript =
                "from com.ziclix.python.sql import zxJDBC\njdbc_url = \"jdbc:splice://localhost:1527/splicedb\"\nzxJDBC.autocommit = 1\nusername = \"splice\"\npassword = \"admin\"\ndriver = \"com.splicemachine.db.jdbc.ClientDriver\"\n\n# Beeter to obtain a connection using the with-statment\ndef run(*args):\n    lim = args[0]\n    conn = zxJDBC.connect(jdbc_url, username, password, driver)\n    c = conn.cursor()\n    stmt = \"select alias, javaclassname from sys.sysaliases {limit ?}\"\n    c.executemany(stmt,[lim])\n    d = c.description\n    result = c.fetchall()\n    conn.commit()\n    c.close()\n    conn.close()\n    return [[d,result]]";
        System.out.println("Execute Python script:");
        System.out.println(pythonScript);
        String funcName = "run";

        PythonInterpreter interpreter = new PythonInterpreter();
        try {
            interpreter.exec(pythonScript);
            PyFunction userFunc = interpreter.get(funcName, PyFunction.class);
            PyObject result = (PyObject) userFunc._jcall(args);
            return;
        }catch (Exception e){
            e.printStackTrace();
        }
        return;
    }



    public float jFun(){
        return 1.1f;
    }

    static ArrayList<String> jyTest(Object... args){
        String pythonScript =
                "from java.lang import String\n" +
                        "from java.lang import reflect\n" +
                        "from JDBCExample import Main\n" +
                        "\n" +
                        "def run(*args):\n" +
                        "    m = Main()\n" +
                        "    print(type(args))\n" +
                        "    print(len(args))\n" +
                        "    print(type(args[0]))\n"+
                        "    print(args[0])\n" +
                        "    print type(Main)\n" +
//                        "    methods = Main.getClass().getDeclareMethods()\n" +
//                        "    print type(methods)\n" +
//                        "    for method in methods:\n" +
//                        "        print(method.toString())\n";
                        "    return m.jFun()\n";
        System.out.println("Execute Python script:");
        System.out.println(pythonScript);
        String funcName = "run";

        PythonInterpreter interpreter = new PythonInterpreter();

        try {
            interpreter.exec(pythonScript);
            PyFunction userFunc = interpreter.get(funcName, PyFunction.class);
            PyObject result = userFunc._jcall(args);
            Object re = result;
            return null;
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    static void callJavaGetProcedure(Object... args){
        Connection conn = null;
        Statement stmt = null;
        try{

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            CallableStatement cStmt = conn.prepareCall("{call splice.get_java(3)}");
            cStmt.execute();
            ResultSet rs = cStmt.getResultSet();
            while(rs.next()){
                String alias = rs.getString("alias");
                String cname = rs.getString("javaclassname");
                System.out.printf("alias %s javaclassname %s\n", alias, cname);
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    static void callPythonGetProcedure(Object... args){
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            CallableStatement cStmt = conn.prepareCall("{call splice.python_get()}");
            cStmt.execute();
            ResultSet rs = cStmt.getResultSet();
            while(rs.next()){
                String alias = rs.getString("alias");
                String cname = rs.getString("javaclassname");
                System.out.printf("alias %s javaclassname %s\n", alias, cname);
            }
            rs.close();
            cStmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Tests ability for stored procedure to return not just a result set,
     * but multiple OUTPUT parameters.
     * @throws Exception
     */
//    @Test
//    public void testProcedureWithMultipleOutputParameters() throws Exception {
//        String employeeTableName = EMPLOYEE_TABLE_NAME_BASE + "11";
//        int rc = 0;
//        ResultSet rs = null;
//
//        // Create the user-defined stored procedures.
//        rc = methodWatcher.executeUpdate(CREATE_PROC_GET_EMPLOYEE_MULTIPLE_OUTPUT_PARAMS);
//        Assert.assertEquals("Incorrect return code or result count returned!", 0, rc);
//
//        // Create the table.
//        rc = methodWatcher.executeUpdate(String.format(CALL_CREATE_EMPLOYEE_TABLE_FORMAT_STRING, employeeTableName));
//        Assert.assertEquals("Incorrect return code or result count returned!", 0, rc);
//
//        CallableStatement stmt = methodWatcher.prepareCall(CALL_GET_EMPLOYEE_MULTIPLE_OUTPUT_PARAMS);
//        stmt.setString(1, employeeTableName);
//        stmt.setLong(2, 12345L); // won't be found
//        stmt.registerOutParameter(3, Types.VARCHAR);
//        stmt.registerOutParameter(4, Types.VARCHAR);
//
//        rs = stmt.executeQuery();
//        Assert.assertEquals("Incorrect # of rows returned!", 0, resultSetSize(rs));
//        Assert.assertEquals("Incorrect error code", "0", stmt.getString(3));
//        Assert.assertEquals("Incorrect error message", "Success", stmt.getString(4));
//        rs.close();
//
//        // Drop the user-defined stored procedures.
//        rc = methodWatcher.executeUpdate(DROP_PROC_GET_EMPLOYEE_MULTIPLE_OUTPUT_PARAMS);
//        Assert.assertEquals("Incorrect return code or result count returned!", 0, rc);
//
//        // Drop the table.
//        rc = methodWatcher.executeUpdate(String.format(CALL_DROP_EMPLOYEE_TABLE_FORMAT_STRING, employeeTableName));
//        Assert.assertEquals("Incorrect return code or result count returned!", 0, rc);
//    }


    static void call_GET_EMPLOYEE_MULTIPLE_OUTPUT_PARAMS(){
//        String stmtStr = "{CALL SPLICE.GET_EMPLOYEE_MULTIPLE_OUTPUT_PARAMS(?, ?, ?, ?)}";
        Connection conn = null;
        try{

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            CallableStatement stmt = conn.prepareCall("{CALL SPLICE.GET_EMPLOYEE_MULTIPLE_OUTPUT_PARAMS(?, ?, ?, ?)}");
            stmt.setString(1, "EMPLOYEE");
            stmt.setLong(2, 12345L); // won't be found
            stmt.registerOutParameter(3, Types.VARCHAR);
            stmt.registerOutParameter(4, Types.VARCHAR);

            ResultSet rs = stmt.executeQuery();
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    static void getTestTableRow(){
        Connection conn = null;
        try{

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from test_table {limit 3}");
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    static void call_GET_EMPLOYEE(){
        Connection conn = null;
        try{

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            CallableStatement stmt = conn.prepareCall("{CALL SPLICE.GET_EMPLOYEE(?, ?)}");
            stmt.setString(1, "EMPLOYEE");
            stmt.setLong(2, 2L);

            ResultSet rs = stmt.executeQuery();
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

//    static void pyCodeTest(){
//        String script ="def run():\n" +
//                "    return \"this is a test\"";
//        PythonInterpreter interpreter = new PythonInterpreter();
//        PyCode interpretedCode = interpreter.compile(script);
//        interpretedCode.__();
//    }

    static void multipleRestulSetsTest(){
        Connection conn = null;
        try{

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            CallableStatement stmt = conn.prepareCall("{CALL SPLICE.TEST_PROC()}");

            stmt.execute();
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

