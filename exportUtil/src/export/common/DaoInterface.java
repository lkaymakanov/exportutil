package export.common;

import export.oracle.dao.DaoOrcl;
import export.postgre.dao.DaoPostgre;

public  interface DaoInterface {

	DaoOrcl getOracleDao();
	DaoPostgre getPostgreDao();
}
