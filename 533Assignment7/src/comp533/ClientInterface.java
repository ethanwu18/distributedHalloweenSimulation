package comp533;

import java.rmi.Remote;
import java.rmi.RemoteException;

import consensus.ProposalFeedbackKind;
import stringProcessors.HalloweenCommandProcessor;
import util.interactiveMethodInvocation.ConsensusAlgorithm;
import util.interactiveMethodInvocation.IPCMechanism;

public interface ClientInterface extends Remote{
	void start(String[] args) throws RemoteException;
	void init(String[] args) throws RemoteException;
	void broadcast(String s) throws RemoteException;
	public HalloweenCommandProcessor createSimulation(String prefix) throws RemoteException;
	void broadcastGIPC(IPCMechanism ipcMechanism) throws RemoteException;
	void broadcastMS(boolean newValue) throws RemoteException;
	void setCmd(HalloweenCommandProcessor commandP1, String[] args) throws RemoteException;
	HalloweenCommandProcessor getCmd() throws RemoteException;
	public void broadcastAtomic(boolean isAtomic) throws RemoteException;
	void broadcastCA(ConsensusAlgorithm conAlg) throws RemoteException;
	void accept(String clientInt, String command) throws RemoteException;
	public void setAccept(ProposalFeedbackKind a) throws RemoteException;

}
