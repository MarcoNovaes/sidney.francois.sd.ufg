
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor2{
	public static void main(String[] args) throws IOException {
		
		//Criando socket do servidor, abrindo aplicação na porta 12345
		ServerSocket socket_servidor_mestre= new ServerSocket(12346);	
		
		while(true)
		{
			try {
				Socket socket_cliente2 = socket_servidor_mestre.accept();
				System.out.println("Nova conexão foi estabelecida " + socket_cliente2.getInetAddress().getHostAddress());
				
				Pessoa empregadonovo = new Pessoa();
				Deserializador deserializa = new Deserializador();
				
				empregadonovo = deserializa.deserializar(socket_cliente2);
				
				System.out.println("Nova Thread sendo inicializada no novo servidor!");
				
				Thread threadDoCliente = new Thread(empregadonovo);
				threadDoCliente.start();
				
			} catch(Exception e) {
				socket_servidor_mestre.close();
				e.printStackTrace();
			}
		
		
		
			//socket_servidor.close();
			//socket_cliente.close();
		}
	}
}