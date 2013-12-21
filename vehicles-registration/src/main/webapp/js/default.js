/*function resetPaging() {
	jQuery('#page').val('1');
}

function incPaging() {
	jQuery('#page').val(jQuery('#page').val()*1 + PAGING);
}

function decPaging() {
	jQuery('#page').val(jQuery('#page').val() - PAGING);
}*/

jQuery(document).ready(function() {
	setCheckbox();
});

function setCheckbox() {
	if (jQuery('#searchVehicleFormBean\\.insuranceCompany').val() == '') {
		jQuery('#searchVehicleFormBean\\.onlyActiveInsurance1').attr('disabled', true);
	} else {
		jQuery('#searchVehicleFormBean\\.onlyActiveInsurance1').removeAttr('disabled');
	}
}
