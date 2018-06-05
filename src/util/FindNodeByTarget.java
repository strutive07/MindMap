package util;

import IO.treeIO;

public class FindNodeByTarget {
    public static treeIO dfs(treeIO node, int target){
        System.out.println("NAME : " + node.getStringName() +  "    |    nodeNumber : " + node.getNodeNumber());
        if(node.getNodeNumber() == target){
            return node;
        }
        for (int i=0; i<node.getChildCount(); i++){
            treeIO next_node = node.getChildAt(i);
            if(next_node != null){
                treeIO result = dfs(next_node, target);
                if(result != null){
                    return result;
                }
            }
        }
        return null;
    }
}
