package edu.eci.escuelaing.arep.networking.httpServer;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class HttpServer {
    private static final HttpServer _instance = new HttpServer();
    public static HttpServer getInstance(){return _instance;}
    private HttpServer(){}
    public void start(String[] args) throws IOException{

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        boolean running=true;
        while (running) {
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            serveConneciton(clientSocket);
        }
        serverSocket.close();
    }

    public void serveConneciton(Socket clientSocket) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        ArrayList<String> request = new ArrayList<String>();
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            request.add(inputLine);
            if (!in.ready()) {
                break;
            }
        }
        System.out.println(request);
        String UrlStr = request.get(0).split(" ")[1];
        outputLine=getResouce(UrlStr);
        out.println(outputLine);
        out.close();
        clientSocket.close();
        in.close();

    }
    public String getResouce(String resourceURL) throws IOException {

        return RequestResponseDisc();
    }
    public String RequestResponseDisc() throws IOException {
        File archivo = new File("public_html/index.html");
        BufferedReader in = new BufferedReader(new FileReader(archivo));
        String str;
        String output = "HTTP/1.1 200 OK\r\nContent - Type: text/html\r\n\r\n";
        while ((str = in.readLine()) != null) {
            System.out.println(str);
            output+=str+"\n";
        }
        System.out.println(output);

        return  output;
    }
    public String RequestDefaultRespone(){
        String outputLine ="HTTP/1.1 200 OK\r\n"
                + "Content - Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "  <head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Title of the document</title>\n"
                + "  </head>\n"
                + "  <body>\n"
                + "    My Web Site\n"
                + "    <img src=\"https://github.com/RichardUG/SparkHerokuApp/blob/master/img/wallper.png?raw=true\">"
                + "  </body>\n"
                + "</html>\n";
        return outputLine;
    }

    public static void main(String[] args) throws IOException{
        HttpServer.getInstance().start(args);
    }
}
