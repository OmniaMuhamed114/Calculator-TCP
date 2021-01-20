package task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) throws IOException {
        InetAddress address = InetAddress.getLocalHost();
        Socket client = new Socket(address, 22000);
        DataOutputStream clientWriteSource = new DataOutputStream(client.getOutputStream());
        DataInputStream clientReadSource = new DataInputStream(client.getInputStream());
        Scanner input = new Scanner(System.in);
        while (true)
        {
            System.out.print("Enter the equation in the form: ");
            System.out.println("'operand operator operand'");
            String inp = input.nextLine();
            if (inp.equals("exit"))
                break;
            clientWriteSource.writeUTF(inp);
            String ans = clientReadSource.readUTF();
            System.out.println("Answer = " + ans);
            System.out.println("input exit to exit..");
        }
        clientWriteSource.close();
        clientReadSource.close();
        input.close();
        client.close();
    }
}