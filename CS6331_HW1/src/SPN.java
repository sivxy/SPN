import java.util.Arrays;

public class SPN {
    public static void main(String[] args) {
        int[] message = FileOperation.readerMessage("File/input_spn.txt");
        int[] key = FileOperation.readerKey("File/input_spn.txt");
        int[] sBox = FileOperation.readerSBox("File/input_spn.txt");
        int[] permutation = FileOperation.readerPermutation("File/input_spn.txt");
        int[] result = new int[message.length];
        int op = FileOperation.readerA("File/input_spn.txt");
        int times = message.length / 32;
        //Encryption
        if(op == 0){
            for(int i = 0; i < times; i++){
                int[] m = Arrays.copyOfRange(message,i * 32,(i + 1) * 32);
                for(int j = 0; j < 8; j++){
                    int[] k = Arrays.copyOfRange(key,j * 32,(j + 1) * 32);
                    int[] u = Key(k, m);
                    int[] v = new int[32];
                    for(int q = 0; q < 4; q++){
                        int[] v1 = Arrays.copyOfRange(u,q * 8,(q + 1) * 8);
                        v1 = SBox(sBox,v1);
                        System.arraycopy(v1,0,v,q * 8,v1.length);
                    }
                    v = Permutation(permutation,v);
                    m = v;
                }
                int[] finalKey = Arrays.copyOfRange(key,256,288);
                int[] cipher = Key(finalKey,m);
                System.arraycopy(cipher,0,result,i * 32,cipher.length);
            }
        }
        //Decryption
        else{
            for(int i = 0; i < times; i++){
                int[] m = Arrays.copyOfRange(message, i * 32, (i + 1) *32);
                for(int j = 8 ; j > 0; j--){
                    int[] k = Arrays.copyOfRange(key,  j * 32, (j + 1) * 32);
                    int[] u = Key(k, m);
                    u = DPermutation(permutation,u);
                    int[] v = new int[32];
                    for(int q = 0; q < 4; q++){
                        int[] v1 = Arrays.copyOfRange(u, q * 8, (q + 1) * 8);
                        v1 = DSBox(sBox,v1);
                        System.arraycopy(v1, 0, v, q * 8, v1.length);
                    }
                    m = v;
                }
                int[] finalKey = Arrays.copyOfRange(key, 0, 32);
                int[] cipher = Key(finalKey,m);
                System.arraycopy(cipher, 0, result, i * 32, cipher.length);
            }
        }

        String operation = "";
        if(op == 0)
            operation += "Encryption";
        else
            operation += "Decryption";
        FileOperation.writeFile(operation, toString(message), toString(key), toStringF(sBox), toStringP(permutation), toString(result));


    }
    //De/Encryption-key
    public static int[] Key(int[] K, int[] m){
        int[] res = new int[m.length];
        for(int i = 0; i < K.length; i++){
            res[i] = K[i]^m[i];
        }
        return res;

    }
    //Encryption-SBox
    public static int[] SBox(int[] box, int[] ex){
        int number = ex[0] * 128 + ex[1] * 64 + ex[2] * 32 + ex[3] * 16 + ex[4] * 8 + ex[5] * 4 + ex[6] * 2 + ex[7];
        number = box[number];
        int[] v = new int[8];
        v[0] = number / 128;
        number = number % 128;
        v[1] = number / 64;
        number = number % 64;
        v[2] = number / 32;
        number = number % 32;
        v[3] = number / 16;
        number = number % 16;
        v[4] = number / 8;
        number = number % 8;
        v[5] = number / 4;
        number = number % 4;
        v[6] = number / 2;
        number = number % 2;
        v[7] = number;
        return v;
    }
    //Decryption-SBox
    public static int[] DSBox(int[] box, int[] ex){
        int number = ex[0] * 128 + ex[1] * 64 + ex[2] * 32 + ex[3] * 16 + ex[4] * 8 + ex[5] * 4 + ex[6] * 2 + ex[7];
        //number = box[number];
        int position;
        for(int i = 0; ;i++){
            if(box[i] == number){
                position = i;
                break;
            }
        }
        number = position;
        int[] v = new int[8];
        v[0] = number / 128;
        number = number % 128;
        v[1] = number / 64;
        number = number % 64;
        v[2] = number / 32;
        number = number % 32;
        v[3] = number / 16;
        number = number % 16;
        v[4] = number / 8;
        number = number % 8;
        v[5] = number / 4;
        number = number % 4;
        v[6] = number / 2;
        number = number % 2;
        v[7] = number;
        return v;
    }
    //Encryption-Permutation
    public static int[] Permutation(int[] permutation, int[] v){
        int[] res = new int[32];
        for(int i = 0; i < v.length;i++){
            res[permutation[i]] = v[i];
        }
        return res;
    }
    //Decryption-Permutation
    public static int[] DPermutation(int[] permutation, int[] v){
        int[] res = new int[32];
        for(int i = 0; i < v.length;i++){
            int position;
            for(int j = 0; ; j++){
                if(permutation[j] == i){
                    position = j;
                    j = 0;
                    break;
                }
            }
            res[position] = v[i];
        }
        return res;
    }
    public static String toString(int[] content){
        String data = "";
        for(int i = 0; i < content.length; i++){
            data += content[i];
        }
        return data;
    }
    public static String toStringF(int[] content){
        String data = "";
        for(int i = 0; i < content.length; i++){
            String str = "f("+i+ ")=" + content[i];
            data += str;
            if(i != content.length -1)
                data += ", ";
        }
        return data;
    }
    public static String toStringP(int[] content){
        String data = "";
        for(int i = 0; i < content.length; i++){
            String str = " σ("+i+ ")=" + content[i];
            data += str;
            if(i != content.length -1)
                data += ", ";
        }
        return data;
    }
}
