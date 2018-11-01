package br.edu.ifpb.collegialis.type;

public enum TipoStatusProcesso {
	JULGADO("Julgado"),
	RETIRADO("Retirado de pauta");
	
	private String texto;

	public String getTexto() {
		return texto;
	}
	
	private TipoStatusProcesso(String texto) {
		this.texto = texto;
	}

}
