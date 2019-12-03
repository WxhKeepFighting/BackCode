package Server;

import java.io.*;
import java.net.Socket;

public class TCPClient01 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8888);
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        String message = "登录验证,1,1";
        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
        String result = dataInputStream.readUTF();
        System.out.println("服务器发送"+result);
        dataInputStream.close();
        dataOutputStream.close();
        socket.close();
    }
}
