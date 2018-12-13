
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
	
	private static int quanidade_threads;
	
	public static void main(String[] args) throws IOException {
				
		//Criando socket do servidor, abrindo aplicação na porta 12345
		ServerSocket socket_servidor = new ServerSocket(12345);	
		
		while(true)
		{
			try {
				Socket socket_cliente = socket_servidor.accept();
				System.out.println("Nova conexão foi estabelecida " + socket_cliente.getInetAddress().getHostAddress());
				
				Pessoa empregadonovo = new Pessoa();
				Deserializador deserializa = new Deserializador();
				
				empregadonovo = deserializa.deserializar(socket_cliente);
				
				System.out.println("Nova Thread sendo inicializada!");
				
				Servidor.quanidade_threads ++;
				
				if(Servidor.quanidade_threads > 3) {
					Socket socket_novo_servidor = new Socket("127.0.0.3", 12346);
					Serializador serializador = new Serializador();
					serializador.serializar(empregadonovo, socket_novo_servidor);
					socket_cliente.close();
					
				} else {
					Thread threadDoCliente = new Thread(empregadonovo);
					threadDoCliente.start();
				}

				
			} catch(Exception e) {
				socket_servidor.close();
				e.printStackTrace();
			}
		
		
		
			//socket_servidor.close();
			//socket_cliente.close();
		}
	}
}