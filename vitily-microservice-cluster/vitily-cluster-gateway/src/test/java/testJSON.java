import com.vitily.common.util.JSONUtil;

import java.util.List;

public class testJSON {
    public static void main(String[] args){
        String str="[\"/mem/**\",\"/score/query\"]";
        List<String> strs = JSONUtil.parseObject(str, List.class);
        System.out.println();
    }
}
