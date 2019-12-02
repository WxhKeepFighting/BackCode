package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CaluteLength extends Thread {
    private Socket socket;
    public CaluteLength(Socket socket){this.socket = socket;}

    @Override
    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();//获取客户端的输入输出流
            InputStream inputStream = socket.getInputStream();
            int ch = 0;
            byte[] buff = new byte[1024];
            //buff来读取输入的内容，ch来获取数组长度
            ch = inputStream.read(buff);
            String content = new String(buff,0,ch);//字节流转为字符串

            int i = 0;
            System.out.println("这是客户端"+content+":"+i);
            while (true){
                i++;
                Thread.sleep(1000);//暂停当前线程，给其他线程使用
                System.out.println("这是客户端"+content+":"+i);
            }
        } catch (InterruptedException|IOException e) {
            e.printStackTrace();
        }
    }
}
