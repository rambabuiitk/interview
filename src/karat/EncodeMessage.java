package karat;

public class EncodeMessage {

    public static char[][] encodeMessage(String message, int row, int col){
        char[][] ans = new char[row][col];
        if (message == null || message.length() == 0) {
            return ans;
        }
        char[] chars = message.toCharArray(); // [t] [h] [i] [s] [I]
        // [s] [A] [T] [e] [s]
        // [t] [f] [o] [r] [E]
        int len = row * col;
        int i = 0;
        while (i < len && i < message.length()){

            int curRow = i / col;   // i = 11 / 4  1
            int curCol = i % col;   // 7 % 5 = 2   11 % 5 = 3

            ans[curRow][curCol] = chars[i];
            i++;
        }

        return  ans;
    }

    public static void main(String[] args){
        String message = "thisIsATestforEcodeMessage";
        char[][] outPut = encodeMessage(message, 4, 5);
        for (int i = 0; i < outPut.length; i++){
            for (int j = 0; j < outPut[0].length; j++){
                System.out.print(outPut[i][j]);
            }
            System.out.println("     ");
        }
    }

}
