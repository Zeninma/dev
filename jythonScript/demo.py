from com.ziclix.python.sql import zxJDBC
jdbc_url = "jdbc:splice://localhost:1527/splicedb"
zxJDBC.autocommit = 1
username = "splice"
password = "admin"
driver = "com.splicemachine.db.jdbc.ClientDriver"

# Beeter to obtain a connection using the with-statment
def run(*args):
    colNum = args[0]
    conn = zxJDBC.connect(jdbc_url, username, password, driver)
    c = conn.cursor()
    qry = "select alias, javaclassname from sys.sysaliases {limit ?}"
    c.executemany(stmt,[colNum])
    d = c.description
    result = c.fetchall()
    conn.commit()
    c.close()
    conn.close()
    return [[d,result]]


"from com.ziclix.python.sql import zxJDBC\njdbc_url = \"jdbc:splice://localhost:1527/splicedb\"\nzxJDBC.autocommit = 1\nusername = \"splice\"\npassword = \"admin\"\ndriver = \"com.splicemachine.db.jdbc.ClientDriver\"\n\n# Beeter to obtain a connection using the with-statment\ndef run(*args):\n    colNum = args[0]\n    conn = zxJDBC.connect(jdbc_url, username, password, driver)\n    c = conn.cursor()\n    qry = \"select alias, javaclassname from sys.sysaliases {limit ?}\"\n    c.executemany(stmt,[colNum])\n    d = c.description\n    result = c.fetchall()\n    conn.commit()\n    c.close()\n    conn.close()\n    return [[d,result]]"
