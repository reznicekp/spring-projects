package org.preznicek.vehiclesregistration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.preznicek.vehiclesregistration.database.domain.codetable.CodeTable;

public class CodeTableHolder {

	private Map<String, List<? extends CodeTable>> codeTableList = null;
	
	public CodeTableHolder() {
		codeTableList = new HashMap<String, List<? extends CodeTable>>();
	}

	public List<? extends CodeTable> getCodeTableData(Class<? extends CodeTable> codeTableClass) {
		String className = codeTableClass.getSimpleName();
		return codeTableList.get(className);
	}
	
	public void addCodeTableData(List<? extends CodeTable> codeTableData) {
		if (codeTableData.size() > 0) {
			String className = codeTableData.get(0).getClass().getSimpleName();
			codeTableList.put(className, codeTableData);
		}
	}
}
