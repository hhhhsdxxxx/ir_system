package Index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 倒排索引的建立
 * Created by Henry on 2015/6/6.
 */
public class ReverseIndex implements Comparable<ReverseIndex>{

    private static int K = 10;
    public static BPlusTree<String, ReverseIndex> root;

    public static void setK(int myk){ K = myk; }

    public static void make_index_vector() {
    }

    //an example, 可以用自己所需的类
    public static List<ReverseIndex> get_ri_vector(int word_id){
        List<ReverseIndex> re = new ArrayList<ReverseIndex>();
        return re;
    }


    private int word_id;
    private String word;
    private List<DocNode> table;
    private boolean isreduce;

    public ReverseIndex(String w){
        word = w;
        table = new ArrayList<DocNode>();
        isreduce = false;
    }

    @Override
    public int compareTo(ReverseIndex o){
        return word_id-o.word_id;
    }

    public boolean isReduce(){
        return isreduce;
    }

    public int numOfDoc(){
        return table.size();
    }

    public void add(DocNode doc){
        table.add(doc);
        isreduce = false;
    }

    public void reduce(){
        if(!table.isEmpty()){
            Collections.sort(table);
            int i, j;
            i = 0;
            j = 1;
            while(i<table.size()&&j<table.size()){
                if(table.get(i).equals(table.get(j))) {
                    table.get(i).plusTf(table.get(j).tf);
                    table.remove(j)

                    ;
                }
                else {
                    i = j;
                    j++;
                }
            }
            isreduce = true;
        }
    }

    public void merge(ReverseIndex tb){
        if(!isreduce)
            reduce();
        if(!tb.isReduce())
            tb.reduce();
        int ptr1, ptr2;
        ptr1 = ptr2 = 0;
        while(ptr1<table.size() && ptr2<tb.table.size()){
            if(table.get(ptr1).equals(tb.table.get(ptr2))){
                table.get(ptr1).plusTf(tb.table.get(ptr2).tf);
                ptr1++;
                ptr2++;
            }
            else{
                table.add(tb.table.get(ptr2));
                if(table.get(ptr1).compareTo(tb.table.get(ptr2))==-1)
                    ptr1++;
                else
                    ptr2++;
            }
        }
        while(ptr2<tb.table.size())
            table.add(tb.table.get(ptr2++));
    }

}
