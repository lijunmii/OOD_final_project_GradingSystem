import java.util.ArrayList;
import java.util.List;

public class SystemDatabase {
    private List<Client> clients;

    SystemDatabase() {
        clients = new ArrayList<>();
    }

    SystemDatabase(String testStr) {
        clients = new ArrayList<>();
        clients.add(new Client("123", "123"));
    }

    public Client getClient(String username) {
        for (Client client : clients) {
            if (client.getUsername().equals(username)) {
                return client;
            }
        }
        return null;
    }

    public Client getClient(String username, String password) {
        for (Client client : clients) {
            if (client.getUsername().equals(username) && client.getPassword().equals(password)) {
                return client;
            }
        }
        return null;
    }

    public boolean usernameExist(String username) {
        for (Client client : clients) {
            if (client.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void register(String username, String password) {
        clients.add(new Client(username, password));
    }
}
