package threadsafe.exemplo5.service;

public class Resultado {

	int processados;
	long tempoMs;
	String modo;

	public Resultado(int processados, long tempoMs, String modo) {
		super();
		this.processados = processados;
		this.tempoMs = tempoMs;
		this.modo = modo;
	}

	public int getProcessados() {
		return processados;
	}

	public void setProcessados(int processados) {
		this.processados = processados;
	}

	public long getTempoMs() {
		return tempoMs;
	}

	public void setTempoMs(long tempoMs) {
		this.tempoMs = tempoMs;
	}

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

}
