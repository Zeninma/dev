CREATE PROCEDURE SPLICE.PYPROC_TYPE_UNIT_TEST() PARAMETER STYLE JAVA READS SQL DATA LANGUAGE PYTHON DYNAMIC RESULT SETS 1 EXTERNAL NAME 'def run(rs):
    c = conn.cursor()
    stmt = "select * from TEST_TABLE {limit 1}"
    c.execute(stmt)
    d = c.description
    result = c.fetchall()
    rs[0] = factory.create([d,result])
    conn.commit()
    c.close()
    conn.close()'
