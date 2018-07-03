import org.python.util.PythonInterpreter;
import org.python.core.*;

import java.sql.*;

public class JythonInJava{
	public static void main(String[] args){
        ResultSet[] re = new ResultSet[1];
		String pythonScript =
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
        "\treturn (c,d,result)";
		System.out.println("Execute Python script:");
		System.out.println(pythonScript);
		String funcName = "run";

		PythonInterpreter interpreter = new PythonInterpreter();

		interpreter.exec(pythonScript);
		PyFunction userFunc = interpreter.get(funcName, PyFunction.class);
		PyTuple result = (PyTuple) userFunc.__call__();
        PyList description = (PyList) result.get(0);
        PyList resultRows = (PyList) result.get(1);
        for (Object resultRow : resultRows) {
            String val1 = (String) ((PyTuple) resultRow).get(0);
            String val2 = (String) ((PyTuple) resultRow).get(1);
            System.out.printf("Alias %s JavaClassName %s\n",val1, val2);
        }
		return;
	}
}
