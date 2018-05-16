package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestClient {
    public static void main(String[] args) {
        try {

            Socket socket = new Socket("localhost", 8888);
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            InputStream is = socket.getInputStream();

            DataInputStream dis = new DataInputStream(is);
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            String info;

            while (true) {
                info = br.readLine();
                dos.writeUTF(info);
                info = dis.readUTF();
                System.out.println("server info:----" + info);
                if("bye".equals(info)){
                    break;
                }
            }

            dis.close();
            dos.close();
            socket.close();
        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(message);
        }
    }
}
