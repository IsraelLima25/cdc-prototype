package com.dev.lima.cdc.site.finalizandocompraparte1;

import java.util.Optional;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dev.lima.cdc.model.Cupom;

public class CupomExistenteENaoExpiradoValidator implements Validator {
	
	private CupomRepository cupomRepository;
	
	public CupomExistenteENaoExpiradoValidator(CupomRepository cupomRepository) {
		this.cupomRepository = cupomRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return DadosCompradorForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
	DadosCompradorForm form = (DadosCompradorForm) target;
	
		if(form.getCodigoCupom() != null) {
			
			Optional<Cupom> possivelCupom = cupomRepository.findByCodigo(form.getCodigoCupom());
			
			if(!possivelCupom.isPresent()) {
				errors.rejectValue("codigoCupom",null,"Este código não existe");
				return;
			}
			
			Cupom cupom = possivelCupom.get();		
			if(!cupom.taValido()) {
				errors.rejectValue("codigoCupom",null,"Infelizmente este cupom expirou");
			}
		}
	
	}

}
