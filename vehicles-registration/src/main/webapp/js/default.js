jQuery(document).ready(function() {
	setCheckbox();
	getModels();
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

/**
 * Ajaxove volani pro ziskani vsech modelu dane znacky. Pri uspesnem vykonani volani databaze a vraceni 
 * seznamu modelu se soucasne modely odstrani a nahradi se novymi (aktualnimi pro vybranou znacku).
 */
function getModels() {
	var brandCode = jQuery('#vehicle\\.brand').val();
	
	if (brandCode > 0) {
		jQuery.ajaxSetup({
			url: '/vehicles-registration/get-models',
			type: 'get',
			data: {'brandCode' : brandCode},
			timeout: 20000,
			success: function(result) {
				jQuery('#models option').remove();
				jQuery('#models').append(result);
			},
			error: function(e) {
				alert('Error during getting list of models! Try to type model by hand.\n' + e.status + ' - ' + e.statusText);
			}
		});
		
		jQuery.ajax();
	}
}
