function resetPaging() {
	jQuery('#page').val('1');
}

function incPaging() {
	jQuery('#page').val(jQuery('#page').val()*1 + PAGING);
}

function decPaging() {
	jQuery('#page').val(jQuery('#page').val() - PAGING);
}