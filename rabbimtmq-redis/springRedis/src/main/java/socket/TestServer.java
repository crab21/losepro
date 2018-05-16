package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    public static void main(String[] args) {
        try {

            ServerSocket s = new ServerSocket(8888);
            Socket socket = s.accept();
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            InputStream is = socket.getInputStream();

            DataInputStream dis = new DataInputStream(is);
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            String info;

            while (true) {
                info = dis.readUTF();

                System.out.println("对方说:" + info);

                if ("bye".equals(info)) {
                    break;
                }
                info = br.readLine();
                dos.writeUTF(info);

                if ("bye".equals(info)) {
                    break;
                }



            }

            dis.close();
            dos.close();
            socket.close();
            s.close();
        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(message);
        }
    }
}
