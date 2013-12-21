package org.preznicek.vehiclesregistration.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.preznicek.vehiclesregistration.utils.Constants;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public class PageableController extends BaseController {

	/**
	 * Zajistuje spravne zobrazeni/skryti strankovacich tlacitek.
	 * @param prefix			Oznacuje tabulku, ke ktere strankovani patri (uplatni se zejmena pokud je na strance vice strankovacich tabulek).
	 * @param itemTotalCount	Celkovy pocet polozek v databazi.
	 * @return					<code>Boolean</code> promenne v instanci tridy <code>HashMap</code>.
	 */
	protected HashMap<String, Boolean> managePagingButtons(String prefix, int currentPageNumber, long itemTotalCount) {
		Boolean pagingPrevVisible = Boolean.TRUE;
		Boolean pagingNextVisible = Boolean.TRUE;
		
		if (currentPageNumber == 1) {
			pagingPrevVisible = Boolean.FALSE;
		}
		
		int pageCount = (int) Math.ceil(new Double(itemTotalCount).doubleValue() / Constants.PAGE_SIZE);
		if (pageCount == 0 || currentPageNumber == pageCount) {
			pagingNextVisible = Boolean.FALSE;
		}
		
		HashMap<String,Boolean> map = new HashMap<String, Boolean>();
		if (StringUtils.isEmpty(prefix)) {
			map.put("pagingPrevVisible", pagingPrevVisible);
			map.put("pagingNextVisible", pagingNextVisible);
		} else {
			map.put(prefix + "_pagingPrevVisible", pagingPrevVisible);
			map.put(prefix + "_pagingNextVisible", pagingNextVisible);
		}
		
		return map;
	}
	
	protected int getCurrentPageNumber(HttpServletRequest request, Pageable pageable) {
		if (request.getParameter("prev") != null) {
			return Math.max(1, pageable.getPageNumber() + 1 - 1);
		} else if (request.getParameter("next") != null) {
			return Math.max(1, pageable.getPageNumber() + 1 + 1);
		} else {
			return 1;
		}
	}
}
