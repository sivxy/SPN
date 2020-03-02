import java.io.*;
import java.util.*;

public class FileOperation {
    public static boolean readFile(String filename, ArrayList<String> data){
        if(filename == null || data == null){
            System.out.println("Filename is null or words is null.");
            return false;
        }
        Scanner scanner;
        try{
            File file = new File(filename);
            if(file.exists()){
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis),"UTF-8");
            }else
                return false;
        }catch(IOException ioe){
            System.out.println("Cannot open" + filename);
            return false;
        }
        if(scanner.hasNextLine()) {
            String contents;
            int i = 0;
            while(scanner.hasNext()){
                contents = scanner.nextLine();
                data.add(contents);
                i++;
            }
        }
        return  true;
    }
    public static int[] readerMessage(String path){
        ArrayList<String> data = new ArrayList<>();
        FileOperation.readFile(path, data);
        int[] message = new int[0];
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).charAt(0) == 'M')
                continue;
            if(data.get(i).charAt(0) =='m'){
                String[] str = data.get(i).split(",");
                int m = Integer.parseInt(str[1]);
                int [] temp = new int[m];
                message = temp;
                continue;
            }
            if(data.get(i).charAt(0) == '#')
                break;
            String[] str = data.get(i).split("");
            for(int j = 0; j < str.length;j++){
                message[j] = Integer.parseInt(str[j]);
            }
        }
        return message;
    }
    public static int[] readerKey(String path){
        ArrayList<String> data = new ArrayList<>();
        FileOperation.readFile(path, data);
        int[] key = new int[288];
        int read = 0;
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).charAt(0) == 'K'){
                read = 1;
                continue;
            }
            if(read == 0)
                continue;
            if(data.get(i).charAt(0) == '#')
                break;
            String[] str = data.get(i).split("");
            for(int j = 0; j < str.length;j++){
                key[j] = Integer.parseInt(str[j]);
            }
        }
        return key;
    }
    public static int[] readerSBox(String path){
        ArrayList<String> data = new ArrayList<>();
        FileOperation.readFile(path, data);
        int[] Sbox = new int[256];
        int read = 0;
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).charAt(0) == 'S'){
                read = 1;
                continue;
            }
            if(read == 0)
                continue;
            if(data.get(i).charAt(0) == '#')
                break;
            String[] str = data.get(i).split(",");
            for(int j = 0; j < str.length;j++){
                Sbox[j] = Integer.parseInt(str[j]);
            }
        }
        return Sbox;
    }
    public static int[] readerPermutation(String path){
        ArrayList<String> data = new ArrayList<>();
        FileOperation.readFile(path, data);
        int[] Permutation = new int[32];
        int read = 0;
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).charAt(0) == 'P'){
                read = 1;
                continue;
            }
            if(read == 0)
                continue;
            if(data.get(i).charAt(0) == '#')
                break;
            String[] str = data.get(i).split(",");
            for(int j = 0; j < str.length;j++){
                Permutation[j] = Integer.parseInt(str[j]);
            }
        }
        return Permutation;
    }
    public static int readerA(String path){
        ArrayList<String> data = new ArrayList<>();
        FileOperation.readFile(path, data);
        int A = -1;
        int read = 0;
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).charAt(0) == 'A'){
                read = 1;
                continue;
            }
            if(read == 0)
                continue;
            if(data.get(i).charAt(0) == '#')
                break;
            String[] str = data.get(i).split("");
            A = Integer.parseInt(str[0]);
        }
        return A;
    }
    public static void writeFile(String operation,String message,String key,String sBox,String permutation,String result){
    try{
        File file = new File("output_spn.txt");
        //if(!file.exists()){
            file.createNewFile();
        //}
        FileWriter fileWritter = new FileWriter(file.getName(),true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(operation);
        bufferWritter.newLine();
        bufferWritter.write("Output:");
        bufferWritter.newLine();
        bufferWritter.write(result);
        bufferWritter.newLine();
        bufferWritter.write("M(message):");
        bufferWritter.newLine();
        bufferWritter.write(message);
        bufferWritter.newLine();
        bufferWritter.write("#");
        bufferWritter.newLine();
        bufferWritter.write("K(key):");
        bufferWritter.newLine();
        bufferWritter.write(key);
        bufferWritter.newLine();
        bufferWritter.write("#");
        bufferWritter.newLine();
        bufferWritter.write("S(sBox):");
        bufferWritter.newLine();
        bufferWritter.write(sBox);
        bufferWritter.newLine();
        bufferWritter.write("#");
        bufferWritter.newLine();
        bufferWritter.write("P(permutation):");
        bufferWritter.newLine();
        bufferWritter.write(permutation);
        bufferWritter.newLine();
        bufferWritter.write("#");
        bufferWritter.newLine();
        //bufferWritter.write(result);
        //bufferWritter.newLine();
        bufferWritter.close();
        System.out.println("Done");
    }catch(IOException e){
        e.printStackTrace();
        }
    }
}
