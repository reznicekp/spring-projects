jQuery(document).ready(function() {
	setCheckbox();
});

/**
 * Nastavuje checkbox ve formulari na vyhledavaci obrazovce pri nacteni obrazovky. Vola se take pri 
 * zmene hodnoty zapsane v text inputu s nazvem pojistovny ve stejnem formulari - checkbox je mozne 
 * zaskrtnout nebo odskrtnout pouze pri vyplnenem text inputu.
 */
function setCheckbox() {
	if (jQuery('#searchVehicleFormBean\\.insuranceCompany').val() == '') {
		jQuery('#searchVehicleFormBean\\.onlyActiveInsurance1').attr('disabled', true);
	} else {
		jQuery('#searchVehicleFormBean\\.onlyActiveInsurance1').removeAttr('disabled');
	}
}
