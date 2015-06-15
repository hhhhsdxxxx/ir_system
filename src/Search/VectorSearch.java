package Search;

import Index.ReverseIndex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henry on 2015/6/6.
 */
public class VectorSearch {

    /**
     * 获得查询结果
     * @param search 查询词
     * @return re 文档的ID列表
     */
    public static List<Integer> getResult(String search){
        List<Integer> re = new ArrayList<Integer>();
        List<Integer> word_id = parse(search);
        //an example, Object可以替换成处理需要的类
        //List<Object> ri = ReverseIndex.get_ri_vector(word_id.get(0));
        return re;
    }

    /**
     * 提取出搜寻词关键信息
     * @param search 搜寻词
     * @return re 词的ID list
     */
    private static List<Integer> parse(String search){
        List<Integer> re = new ArrayList<Integer>();
        return re;
    }

    /**
     * 对查询结果的排序
     * @param doc_id 结果文档列表
     */
    private static void sort_docid(List<Integer> doc_id){

    }
}
