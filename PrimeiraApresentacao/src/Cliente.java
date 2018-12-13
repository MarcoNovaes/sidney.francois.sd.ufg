
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		//Criando o socket do cliente para que o cliente faça a conexão com o servidor
		Socket socket_cliente = new Socket("127.0.0.1", 12345);
		
		//Assim que o cliente se conectar começar a serialização do objeto
		Pessoa novoempregado = new Pessoa("Pedro", 23);
		Serializador serializador = new Serializador();
		
		serializador.serializar(novoempregado, socket_cliente);
		
		socket_cliente.close();
	}
}