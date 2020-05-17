package comp533;

import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

import inputport.nio.manager.NIOManager;
import inputport.nio.manager.NIOManagerFactory;

public class ServerReadThread implements Runnable {
	protected ArrayBlockingQueue<Message> readQueue;
	protected NIOManager nioManager = NIOManagerFactory.getSingleton();
	protected Set<SocketChannel> socket;

	public ServerReadThread(ArrayBlockingQueue<Message> readQueue, Set<SocketChannel> socket) {
		this.readQueue = readQueue;
		this.socket = socket;
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
			for (SocketChannel s : socket) {
				nioManager.write(s, message.getBuffer());
			}
		}
	}
}
