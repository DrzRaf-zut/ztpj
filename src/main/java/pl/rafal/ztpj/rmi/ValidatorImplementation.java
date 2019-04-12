package pl.rafal.ztpj.rmi;

import pl.rafal.ztpj.Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ValidatorImplementation extends UnicastRemoteObject implements Validator {

    Map memberMap;
    String token;

    public ValidatorImplementation() throws RemoteException {
        super();
        memberMap = new HashMap();
        memberMap.put("ztpj", "ztpj");
        memberMap.put("Rafal", "1234");
    }

    @Override
    public String validate(String aUserName, String aPassword){
        if(existsInInternalDB(aUserName,aPassword)){
            SecureRandom random = new SecureRandom();
            byte bytes[] = new byte[20];
            random.nextBytes(bytes);
            String genToken = bytes.toString();
            byte[] encodedBytes = Base64.getEncoder().encode(genToken.getBytes());
            token = new String(encodedBytes);
            Server.tokens.put(token, System.currentTimeMillis());
            return token;
        }

        token = "";

        return token;
    }

    private boolean existsInInternalDB(String username, String password) {
        return memberMap.containsKey(username) && memberMap.get(username).equals(password);
    }
}
