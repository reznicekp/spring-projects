package org.preznicek.vehiclesregistration.database.dao;

import java.util.List;

import org.preznicek.vehiclesregistration.database.domain.codetable.CodeTable;
import org.springframework.stereotype.Repository;

@Repository
public class CodeTableDao extends BaseDao {

	public void fillCodeTable(List<? extends CodeTable> codeTable) {
		getSession();
		
		for (CodeTable ct : codeTable) {
			session.saveOrUpdate(ct);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<? extends CodeTable> getCodeTableData(Class<? extends CodeTable> codeTableName) {
		getSession();
		
		return session.createCriteria(codeTableName).list();
	}
}
