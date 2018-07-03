from com.ziclix.python.sql import zxJDBC
jdbc_url = "jdbc:splice://localhost:1527/splicedb"
zxJDBC.autocommit = 1
username = "splice"
password = "admin"
driver = "com.splicemachine.db.jdbc.ClientDriver"

# obtain a connection using the with-statment
def run(*args):
    col_name = args[0]
    print(type(col_name))
    print(col_name)
    conn = zxJDBC.connect(jdbc_url, username, password, driver)
    c = conn.cursor()
    stmt = "select * from sys.sysaliases where alias=?"
    print("this " + col_name)
    result1= c.execute(stmt, [col_name])
    d = c.description
    result2 = c.fetchall()
    conn.commit()
    c.close()
    conn.close()
    return (c, d, result1, result2)
