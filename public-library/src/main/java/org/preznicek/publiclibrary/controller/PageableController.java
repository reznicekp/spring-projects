package org.preznicek.publiclibrary.controller;

import java.util.HashMap;

import org.preznicek.publiclibrary.model.formbean.PageableFormBean;
import org.preznicek.publiclibrary.utils.Constants;

public class PageableController extends BaseController {

	/**
	 * Zajistuje spravne zobrazeni/skryti strankovacich tlacitek.
	 * @param formBean			Formular s udaji, podle kterych byly nalezeny zaznamy v databazi, ktere se strankuji.
	 * @param itemTotalCount	Celkovy pocet polozek v databazi.
	 * @return					<code>Boolean</code> promenne v instanci tridy <code>HashMap</code>.
	 */
	protected HashMap<String, Boolean> managePagingButtons(PageableFormBean formBean, long itemTotalCount) {
		Boolean pagingPrevVisible = Boolean.TRUE;
		Boolean pagingNextVisible = Boolean.TRUE;
		
		if (Integer.parseInt(formBean.getPage()) == 1) {
			pagingPrevVisible = Boolean.FALSE;
		}
		
		int pageCount = (int) Math.ceil(new Double(itemTotalCount).doubleValue() / Constants.PAGING);
		int currentPage = (int) Math.ceil(Double.parseDouble(formBean.getPage()) / Constants.PAGING);
		if (pageCount == 0 || currentPage == pageCount) {
			pagingNextVisible = Boolean.FALSE;
		}
		
		HashMap<String,Boolean> map = new HashMap<String, Boolean>();
		map.put("pagingPrevVisible", pagingPrevVisible);
		map.put("pagingNextVisible", pagingNextVisible);
		
		return map;
	}
}
