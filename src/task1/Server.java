package task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server {
    public static void main(String args[]) throws IOException {
        ServerSocket server = new ServerSocket(22000);
        Socket client = server.accept();
        System.out.println("connected......");
        DataInputStream clientReadSource = new DataInputStream(client.getInputStream());
        DataOutputStream clientWriteSource = new DataOutputStream(client.getOutputStream());
        while(true){
            String input = clientReadSource.readUTF();
            if(input.equals("exit"))
                break;
            System.out.println("Equation received:-" + input);
            float result = 0;
            StringTokenizer st = new StringTokenizer(input);
            float operand1 = Integer.parseInt(st.nextToken());
            String operation = st.nextToken();
            float operand2 = Integer.parseInt(st.nextToken());
            if (operation.equals("+")) {
                result = operand1 + operand2 ;
                System.out.println("Sending the result...");
                clientWriteSource.writeUTF(Float.toString(result));
            }
            else if (operation.equals("-")) {
                result = operand1 - operand2;
                System.out.println("Sending the result...");
                clientWriteSource.writeUTF(Float.toString(result));
            }
            else if (operation.equals("*")) {
                result = operand1 * operand2;
                System.out.println("Sending the result...");
                clientWriteSource.writeUTF(Float.toString(result));
            }
            else {
                String error = null;
                if(operand2==0){
                    error ="Error occurred.Denominator should not be zero.";
                    clientWriteSource.writeUTF(error);
                }
                else if(error == null){
                    result = operand1 / operand2;
                    System.out.println("Sending the result...");
                    clientWriteSource.writeUTF(Float.toString(result));
                }
            }
        }
        clientWriteSource.close();
        clientReadSource.close();
        client.close();
        server.close();
    }
}
