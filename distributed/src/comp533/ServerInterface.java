package comp533;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import consensus.ProposalFeedbackKind;
import util.interactiveMethodInvocation.ConsensusAlgorithm;
import util.interactiveMethodInvocation.IPCMechanism;

public interface ServerInterface extends Remote, Serializable {

	public void start() throws RemoteException;
	public void setClients(ClientInterface client, String str) throws RemoteException;
	public void broadcast(String cli, String str) throws RemoteException;
	void broadcastGIPC(String clientInt, IPCMechanism ipcMechanism) throws RemoteException;
	void setClientsGIPC(GIPCclientInt clientInt, String str) throws RemoteException;
	public void broadcastMS(String name, boolean newValue) throws RemoteException;
	void init() throws RemoteException;
	public Object getCmd() throws RemoteException;
	public void setClientsNIO(ClientInterface clientNIO, String cliname) throws RemoteException;
	public void broadcastAtomic(String clientInt, boolean isAtomic) throws RemoteException;
	public void broadcastCA(String name, ConsensusAlgorithm conAlg) throws RemoteException;
	public void broadcastSync(String name, String newValue) throws RemoteException;
	public void returnAccept(ProposalFeedbackKind success) throws RemoteException;
	public void setAccept(ProposalFeedbackKind a) throws RemoteException;

}
	

