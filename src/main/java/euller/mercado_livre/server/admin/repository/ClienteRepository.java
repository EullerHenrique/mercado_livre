package euller.mercado_livre.server.admin.repository;

import com.google.gson.Gson;
import euller.mercado_livre.server.admin.model.Cliente;
import euller.mercado_livre.server.admin.service.mosquitto.MosquittoService;
import org.eclipse.paho.client.mqttv3.MqttException;
import java.util.Hashtable;
import java.util.logging.Logger;

public class ClienteRepository {

    private final Logger logger = Logger.getLogger(ClienteRepository.class.getName());

    private final Hashtable<String, String> clientes = new Hashtable<>();
    private final MosquittoService mosquittoService = new MosquittoService();

    public String criarCliente(Cliente cliente, boolean otherServerUpdate) {
        logger.info("Criando cliente: "+cliente+"\n");
        String CID = cliente.getCID();
        if(!clientes.containsKey(CID)) {
            Gson gson = new Gson();
            String clienteJson = gson.toJson(cliente);
            clientes.put(CID, clienteJson);
            String clienteBD = buscarCliente(CID);
            if (!otherServerUpdate) {
                try {
                    mosquittoService.publish("server/admin/cliente/criar", clienteBD);
                } catch (MqttException e) {
                    throw new RuntimeException(e);
                }
            }
            return clienteBD;
        }
        return null;
    }

    public String modificarCLiente(Cliente cliente, boolean otherServerUpdate) {
        logger.info("Modificando cliente: "+cliente+"\n");
        String CID = cliente.getCID();
        Gson gson = new Gson();
        String clienteJson = gson.toJson(cliente);
        if(clientes.containsKey(CID)){
            clientes.remove(CID);
            clientes.put(CID, clienteJson);
            String clienteBD = buscarCliente(CID);
            if(!otherServerUpdate) {
                try {
                    mosquittoService.publish("server/admin/cliente/modificar",clienteBD);
                } catch (MqttException e) {
                    throw new RuntimeException(e);
                }
            }
            return clienteBD;
        }
        return null;
    }

    public String buscarCliente(String CID){
        logger.info("Buscando cliente: "+CID+"\n");
        if(clientes.containsKey(CID)) {
            return clientes.get(CID);
        }
        return null;
    }

    public String isCliente(String CID){
        logger.info("Verificando cliente: "+CID+"\n");
        if(clientes.containsKey(CID)) {
            return "true";
        }
        return "false";
    }

    public String apagarCliente(String CID, boolean otherServerUpdate){
        logger.info("Apagando cliente: "+CID+"\n");
        if (clientes.containsKey(CID)) {
            clientes.remove(CID);
            if(!otherServerUpdate) {
                try {
                    mosquittoService.publish("server/admin/cliente/apagar", CID);
                } catch (MqttException e) {
                    throw new RuntimeException(e);
                }
            }
            return "Cliente apagado";
        }
        return null;
    }
}
