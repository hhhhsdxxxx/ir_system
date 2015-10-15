package Search;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Henry Huang on 2015/6/29.
 */
public class BoolSearch {
    public static final int OR = 0;
    public static final int AND = 1;
    public static final int NOT = 2;
    public static final int LP = 3;
    public static final int RP = 4;

    private String sentence;
    //private List<booloperation> re;
    private Stack<String> wordset;
    private Stack<Integer> op;

    public BoolSearch(String sentence){
        this.sentence = sentence;
        re = new LinkedList<booloperation>();
        wordset = new Stack<String>();
        op = new Stack<Integer>();
    }

    class booloperation {
        String op1, op2;
        int op;

        public booloperation(String op1, String op2, int op){
            this.op1 = op1;
            this.op2 = op2;
            this.op  = op;
        }

        String get_result(){
            switch(op) {
                case OR: return op1;
                case AND: return op2;
                case NOT: return op1+op2;
                case LP: return op2+op1;
                default: return null;
            }
        }
    }

    void parse(){
        StringBuilder mys = new StringBuilder();
        boolean is_taken = false;
        //String[] words = sentence.split(" ");
        for(int i = 0; i < sentence.length(); i++){
            char ch = sentence.charAt(i);
            if(ch == ' ') {
                String tmp = mys.toString();
                if(tmp.equals("AND")) {
                    if(op.peek()>AND && op.peek()!=LP)
                        make_op();
                    op.push(AND);
                }
                else if(tmp.equals("OR")){
                    if(op.peek()>OR && op.peek() != LP)
                        make_op();
                    op.push(OR);
                }
                else if(tmp.equals("NOT"))
                    op.push(NOT);
                else{
                    wordset.push(tmp);
                }
                mys.delete(0, mys.length()-1);
            }
            else if(ch == '('){
                op.push(LP);
            }
            else if(ch == ')'){
                make_op_rp();
            }
            else {
                mys.append(ch);
            }
        }
    }

    void make_op(){
        int operation = op.pop();
        if(operation != NOT) {
            String op1 = wordset.pop();
            String op2 = wordset.pop();
            wordset.push(new booloperation(op1, op2, operation).get_result());
        }
        else{
            String op1 = wordset.pop();
            wordset.push(new booloperation(op1, null, operation).get_result());
        }
    }

    void make_op_rp(){
        while(op.peek()!= LP)
            make_op();
        op.pop();
    }
}
