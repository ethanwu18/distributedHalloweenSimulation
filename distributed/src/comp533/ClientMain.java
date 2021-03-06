package comp533;

import java.rmi.NotBoundException;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;
import port.ATracingConnectionListener;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import assignments.util.mainArgs.ClientArgsProcessor;
import util.trace.bean.BeanTraceUtility;
import util.trace.factories.FactoryTraceUtility;
import util.trace.misc.ThreadDelayed;
import util.trace.port.consensus.ConsensusTraceUtility;
import util.trace.port.nio.NIOTraceUtility;
import util.trace.port.rpc.gipc.GIPCRPCTraceUtility;
import util.trace.port.rpc.rmi.RMITraceUtility;

import util.annotations.Tags;
import util.interactiveMethodInvocation.IPCMechanism;
import util.tags.DistributedTags;
@Tags({DistributedTags.CLIENT, DistributedTags.RMI, DistributedTags.NIO, DistributedTags.GIPC})

public class ClientMain {

	protected static GIPCRegistry gipcRegistry;
	
	public static void main(String[] args) {
		startTracing();
		
		java.rmi.registry.Registry registry;
		try {
			int port = ClientArgsProcessor.getRegistryPort(args);
			String host = ClientArgsProcessor.getRegistryHost(args);
			String cliname = ClientArgsProcessor.getClientName(args);
			registry = LocateRegistry.getRegistry(host, port);
			util.trace.port.rpc.rmi.RMIRegistryLocated.newCase(registry, host, port, registry);
			String name = ServerInterface.class.getName();

			//gipc
			gipcRegistry = GIPCLocateRegistry.getRegistry(ClientArgsProcessor.getServerHost(args), ClientArgsProcessor.getGIPCPort(args), cliname);
			if (gipcRegistry == null) {
				System.err.println("Could not connect to server :"
						+ ClientArgsProcessor.getServerHost(args) + ":" + ClientArgsProcessor.getGIPCPort(args));
				System.exit(-1);
			}
			ServerInterface serverGIPC = (ServerInterface) gipcRegistry.lookup(ServerInterface.class, name);
			/*
			 * In GIPC connect listeners can be added for the underlying data channel
			 */
			
			ServerInterface server = (ServerInterface) registry.lookup(name);
			util.trace.port.rpc.rmi.RMIObjectLookedUp.newCase(registry, server, name, registry);
			ClientInterface client = new Client(ClientArgsProcessor.getNIOServerPort(args), server, IPCMechanism.RMI);
			UnicastRemoteObject.exportObject(client, 0);
			server.setClients(client, cliname);
			
			GIPCclientInt clientGIPC = new Client(ClientArgsProcessor.getNIOServerPort(args), serverGIPC, IPCMechanism.GIPC);
			serverGIPC.setClientsGIPC(clientGIPC, cliname);
			gipcRegistry.getInputPort().addConnectionListener(
					new ATracingConnectionListener(gipcRegistry.getInputPort()));
			
			
			//nio
			ClientInterface clientNIO = new Client(ClientArgsProcessor.getNIOServerPort(args), server, IPCMechanism.NIO);
			UnicastRemoteObject.exportObject(clientNIO, 0);
			server.setClientsNIO(clientNIO, cliname);
			//FInish setting clients to gipc server register.
			client.init(args);
			clientGIPC.setCmd(client.getCmd(), args);
			clientNIO.setCmd(client.getCmd(), args);
			client.start(args);


		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	static void startTracing() {
//		PortTraceUtility.setTracing();
//		RMITraceUtility.setTracing();
//		NIOTraceUtility.setTracing();
//		FactoryTraceUtility.setTracing();		
//		ConsensusTraceUtility.setTracing();
//		ThreadDelayed.enablePrint();
//
//		FactoryTraceUtility.setTracing();
//		BeanTraceUtility.setTracing();
//		RMITraceUtility.setTracing();
//		ConsensusTraceUtility.setTracing();
//		ThreadDelayed.enablePrint();
//		GIPCRPCTraceUtility.setTracing();
//		NIOTraceUtility.setTracing();
//		
		NIOTraceUtility.setTracing();
		FactoryTraceUtility.setTracing();
		BeanTraceUtility.setTracing();
		RMITraceUtility.setTracing();
		ConsensusTraceUtility.setTracing();
		ThreadDelayed.enablePrint();
		GIPCRPCTraceUtility.setTracing();


	}
}
