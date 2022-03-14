package netgames;

import javax.swing.JOptionPane;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public class AtorNetgames implements OuvidorProxy {
	
	private static final long serialVersionUID = 1L;
	protected Proxy proxy;
	protected boolean connected;
	protected InterfaceJogador interfaceJogador;
	
	
	public AtorNetgames(InterfaceJogador interfaceJogador) {
		super();
		this.proxy = Proxy.getInstance();
		proxy.addOuvinte(this);	
		connected = false;
		this.interfaceJogador = interfaceJogador;
	}
	
	public void defineConnected(boolean value) {
		this.connected = value;
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public String connect(String server, String name) {
			try {
				proxy.conectar(server, name);
				

				if (connected) {
					
				}
			} catch (JahConectadoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Voce ja esta conectado";
			} catch (NaoPossivelConectarException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Nao foi possivel conectar";
			} catch (ArquivoMultiplayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Voce esqueceu o arquivo de propriedades";
			}
			return "Sucesso: conectado a Netgames Server";
		
	}

	public String disconnect() {
			try {
				proxy.desconectar();
				defineConnected(false);
			} catch (NaoConectadoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "Voce nao esta conectado";
			}
			return "Sucesso: desconectado de Netgames Server";
	}

	public String startMatch() {
		try {
			proxy.iniciarPartida(new Integer(2)); // supondo 2 jogadores, o que pode ser alterado
		} catch (NaoConectadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Falha ao tentar enviar solicitacao de inicio enviada a Netgames Server";
		}
		return "Sucesso: solicitacao de inicio enviada a Netgames Server";
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		// TODO Auto-generated method stub
		int opponentIndex = 1;
		if (posicao.equals(1)) opponentIndex = 2;
		String opponent = proxy.obterNomeAdversario(opponentIndex);
		
		interfaceJogador.requestMatchStart(posicao, opponent);
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		interfaceJogador.endMatch();
		
	}

	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub
	}

	@Override
	public void receberJogada(Jogada jogada) {
		interfaceJogador.receiveMove((Move)jogada);
	}

	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub
		
	}
	
	public void sendMove(Move move) throws NaoJogandoException {
		proxy.enviaJogada(move);
	}
	
	public void endMatch() {
		try {
			proxy.finalizarPartida();
		} catch (NaoConectadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaoJogandoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
