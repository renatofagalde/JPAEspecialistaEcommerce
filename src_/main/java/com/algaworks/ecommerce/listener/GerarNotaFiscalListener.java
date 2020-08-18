package com.algaworks.ecommerce.listener;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.service.NotaFiscalService;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class GerarNotaFiscalListener {

	private NotaFiscalService notaFiscalService = new NotaFiscalService();


	//https://www.algaworks.com/aulas/3241/listeners-para-eventos-do-ciclo-de-vida/
	@PrePersist
	@PreUpdate
	public void gerar(Pedido pedido) {
		if (pedido.isPago() && pedido.getNotaFiscal() == null) {
			notaFiscalService.gerar(pedido);
		}
	}
}
