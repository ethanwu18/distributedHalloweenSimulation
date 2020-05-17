package comp533;

import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;

public class ClientReadThread implements Runnable {
	protected ArrayBlockingQueue<Message> readQueue;
	
	public ClientReadThread(ArrayBlockingQueue<Message> readQueue) {
		this.readQueue = readQueue;
		
	}

	@Override
	public void run() {
		Message message = null;

		while (true) {
			try {
				message = readQueue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ByteBuffer buffer = message.getBuffer();
			String command = new String(buffer.array(), buffer.position(), buffer.array().length);
			
			util.trace.port.consensus.ProposalLearnedNotificationReceived.newCase(this, util.trace.port.consensus.communication.CommunicationStateNames.COMMAND,-1, command);
			util.trace.port.consensus.ProposedStateSet.newCase(this, util.trace.port.consensus.communication.CommunicationStateNames.COMMAND,-1, command);
		}
	}
}
