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
	
	/**
	 * Zavola DAO pro ulozeni dat ciselniku do databaze.
	 * @param codeTable		Data ukladaneho ciselniku.
	 */
	@Transactional
	public void fillCodeTable(List<? extends CodeTable> codeTable) {
		codeTableDao.fillCodeTable(codeTable);
	}
	
	/**
	 * Vrati data daneho ciselniku z <code>codeTableHolder</code>. Pokud tam jeste nejsou ulozena, 
	 * dotahnou se z databaze a ulozi se do <code>codeTableHolder</code>.
	 * @param codeTableClass	Trida urcujici ciselnik.
	 * @return					Data ciselniku.
	 */
	@Transactional
	public List<? extends CodeTable> getCodeTableData(Class<? extends CodeTable> codeTableClass) {
		List<? extends CodeTable> codeTableData = codeTableHolder.getCodeTableData(codeTableClass);
		
		if (codeTableData == null) {
			codeTableData = codeTableDao.getCodeTableData(codeTableClass);
			codeTableHolder.addCodeTableData(codeTableData);
		}
		
		return codeTableData;
	}
	
	/**
	 * Vrati zaznam z ciselniku. Pokud zaznam neni nalezen, vrati <code>null</code>.
	 * @param codeTableClass	Trida urcujici ciselnik.
	 * @param code				Kod urcujici zaznam ciselniku.
	 * @return					Zaznam z ciselniku.
	 */
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
