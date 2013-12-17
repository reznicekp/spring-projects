package org.preznicek.vehiclesregistration.database.service;

import java.util.List;

import org.preznicek.vehiclesregistration.CodeTableHolder;
import org.preznicek.vehiclesregistration.database.dao.CodeTableDao;
import org.preznicek.vehiclesregistration.database.domain.codetable.CodeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CodeTableService {

	@Autowired
	private CodeTableDao codeTableDao;
	@Autowired
	private CodeTableHolder codeTableHolder;
	
	@Transactional
	public void fillCodeTable(List<? extends CodeTable> codeTable) {
		codeTableDao.fillCodeTable(codeTable);
	}
	
	@Transactional
	public List<? extends CodeTable> getCodeTableData(Class<? extends CodeTable> codeTableClass) {
		List<? extends CodeTable> codeTableData = codeTableHolder.getCodeTableData(codeTableClass);
		
		if (codeTableData == null) {
			codeTableData = codeTableDao.getCodeTableData(codeTableClass);
			codeTableHolder.addCodeTableData(codeTableData);
		}
		
		return codeTableData;
	}
	
	public CodeTable getCodeTableRow(Class<? extends CodeTable> codeTableClass, Integer code) {
		List<? extends CodeTable> codeTableData = getCodeTableData(codeTableClass);
		
		for (CodeTable codeTable : codeTableData) {
			if (code.equals(codeTable.getCode())) {
				return codeTable;
			}
		}
		
		return null;
	}
}
