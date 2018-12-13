
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/*	Classe usada para deserializar os objetos que
 * 	o cliente receberá do servidor
 * */

public class Deserializador {
	
	public Pessoa deserializar(Socket remetente) throws IOException {
		
		ObjectInputStream entrada = new ObjectInputStream(remetente.getInputStream());
		Pessoa novoempregado = new Pessoa();
		try {
			novoempregado = (Pessoa) entrada.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		entrada.close();
		return novoempregado;
	}
	
	public Object deserializarEmArquivo() throws ClassNotFoundException, IOException {
		
		FileInputStream objetoRecebido = new FileInputStream("dados.ser");
		ObjectInputStream entrada = new ObjectInputStream(objetoRecebido);
		
		Object objeto_deserializado = entrada.readObject();
		entrada.close();
		
		return objeto_deserializado;
	}
}