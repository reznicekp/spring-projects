package org.preznicek.vehiclesregistration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.preznicek.vehiclesregistration.database.domain.codetable.CodeTable;
import org.preznicek.vehiclesregistration.database.service.CodeTableService;

/**
 * Vyuziva se v {@link CodeTableService} pro uchovani dat z ciselniku.
 */
public class CodeTableHolder {

	private Map<String, List<? extends CodeTable>> codeTableList = null;
	
	/**
	 * Konstruktor, ktery zajisti inicializaci <code>codeTableList</code>.
	 */
	public CodeTableHolder() {
		codeTableList = new HashMap<String, List<? extends CodeTable>>();
	}

	/**
	 * Vrati hodnoty ciselniku.
	 * @param codeTableClass	Oznacuje ciselnik, jehoz hodnoty se maji ziskat.
	 * @return					Hodnoty ciselniku.
	 */
	public List<? extends CodeTable> getCodeTableData(Class<? extends CodeTable> codeTableClass) {
		String className = codeTableClass.getSimpleName();
		return codeTableList.get(className);
	}
	
	/**
	 * Ulozi data ciselniku do promenne <code>codeTableList</code>, ze ktere jsou hodnoty pristupne 
	 * kdykoliv pri behu aplikace.
	 * @param codeTableData		Oznacuje ciselnik, ktery se ma pridat do seznamu ciselniku.
	 */
	public void addCodeTableData(List<? extends CodeTable> codeTableData) {
		if (codeTableData.size() > 0) {
			String className = codeTableData.get(0).getClass().getSimpleName();
			codeTableList.put(className, codeTableData);
		}
	}
}
