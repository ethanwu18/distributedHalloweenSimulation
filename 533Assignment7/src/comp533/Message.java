package comp533;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Message {
	
	private ByteBuffer buffer;
	private SocketChannel socket;
	public Message(SocketChannel socket, ByteBuffer buffer) {
		this.buffer = buffer;
		this.socket = socket;
	}
	
	public SocketChannel getSocket() {
		return socket;
	}
	public void setSoc(SocketChannel s) {
		this.socket = s;
	}
	public ByteBuffer getBuffer() {
		return buffer;
	}
	public void setBuffer(ByteBuffer buffer) {
		this.buffer = buffer;
	}
	
}
