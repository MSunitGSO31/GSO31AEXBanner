/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import aexbanner.MockEffectenbeurs;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Martijn
 */
public class RMIServer {

    // Set port number
    private static final int portNumber = 1099;

    // Set binding name for MockEffectenbeurs
    private static final String bindingName = "mockEffectenBeurs";

    // References to registry and MockEffectenbeurs
    private Registry registry = null;
    private MockEffectenbeurs meb = null;

    // Constructor
    public RMIServer() {

        // Print port number for registry
        System.out.println("Server: Port number " + portNumber);

        // Create MockEffectenbeurs
        try {
            meb = new MockEffectenbeurs();
            System.out.println("Server: Student administration created");
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create student administration");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            meb = null;
        }

        // Create registry at port number
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Bind MockEffectenbeurs using registry
        try {
            registry.rebind(bindingName, meb);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind student administration");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Welcome message
        System.out.println("SERVER USING REGISTRY");

        // Create server
        RMIServer server = new RMIServer();
    }
}

