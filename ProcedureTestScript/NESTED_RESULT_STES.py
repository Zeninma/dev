def run(procTxt, rs1, rs2, rs3, rs4, rs5, rs6):
    # procTxt should be the name of the stored procedure
    c = conn.cursor()
    c.call(procTxt,[])
