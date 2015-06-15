import Index.BPlusTree;
import Index.Node;
import Index.ReverseIndex;
import Search.VectorSearch;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Henry on 2015/6/6.
 */
public class IR_system {
    private String search_words = null;
    /**
     * 主函数
     * @param args 命令行参数
     */
    public static void main(String []args){
        IR_system getcommand = new IR_system();
        Scanner in = new Scanner(System.in);
        String cmd;
        while(!(cmd=in.nextLine()).equalsIgnoreCase("quit"))
            getcommand.do_operation(getcommand.parse(cmd));
        in.close();
    }

    /**
     * 命令解析
     * @param cmd 命令
     * @return 解析结果
     * op words
     */
    public int parse(String cmd){
        int k = cmd.indexOf(' ');
        String op = cmd.substring(0, k==-1?cmd.length():k);
        if(op.equalsIgnoreCase("search")) {
            int begin = cmd.indexOf('\"');
            int end = cmd.lastIndexOf('\"');
            if(begin == -1 || begin == end)
                System.out.println("You might loss \" in your search!");
            else {
                search_words = cmd.substring(cmd.indexOf('\"') + 1, cmd.lastIndexOf('\"'));
                System.out.println(search_words);
            }
            return 1;
        }
        else if(op.equalsIgnoreCase("make_vector")){
            return 0;
        }
        else {
            System.out.println("Syntactic error!");
        }
        return -1;
    }

    /**
     * 根据命令做相应的操作
     * @param cmd 解析后的命令
     */
    public void do_operation(int cmd){
        switch(cmd){
            case 0:
                ReverseIndex.make_index_vector();
                break;
            case 1:
                List<Integer> re = VectorSearch.getResult(search_words);
                for(int i: re)
                    System.out.println(i);
                break;
        }
    }
}
