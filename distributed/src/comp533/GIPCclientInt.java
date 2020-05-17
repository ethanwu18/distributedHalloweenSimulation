package comp533;

import java.rmi.RemoteException;

import consensus.ProposalFeedbackKind;
import stringProcessors.HalloweenCommandProcessor;
import util.interactiveMethodInvocation.ConsensusAlgorithm;
import util.interactiveMethodInvocation.IPCMechanism;

public interface GIPCclientInt {
	void start(String[] args);
	void init(String[] args);
	void broadcast(String s);
	public HalloweenCommandProcessor createSimulation(String prefix);
	void broadcastGIPC(IPCMechanism ipcMechanism);
	void broadcastMS(boolean newValue);
	void setCmd(HalloweenCommandProcessor commandP1, String[] args);
	HalloweenCommandProcessor getCmd();
	public void broadcastAtomic(boolean isAtomic);
	void broadcastCA(ConsensusAlgorithm conAlg);
	void accept(String clientInt, String command);
	public void setAccept(ProposalFeedbackKind a);


}
