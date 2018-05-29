package algorithm;
import IO.treeIO;
import layout.MainLayout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SetPosition {
    ArrayList<treeIO> root_list;
    treeIO a=root_list.get(0);

    public SetPosition(){
        root_list=layout.MainLayout.getTree();
        start_SetPosition();
    }

    public void start_SetPosition() {
        if (root_list.size() == 1)
            make_grahp();
        else if (root_list.size()>=2)
            make_tree();
    }

    public void make_tree(){

    }

    public void make_grahp(){
        int child_cnt;
        int tree_height=getTreeHeight(root_list.get(0));
        Queue q=new LinkedList();
        treeIO now_node=root_list.get(0);

        q.offer(now_node);

        while (true){
            now_node=(IO.treeIO)q.peek();
            int sibling_cnt=now_node.getChildCount();

            now_node.setX(1);
            now_node.setY(1);
            now_node.setW(1);
            now_node.setH(1);//여기 있는 것들은 차후 width,height값에 따라 변경

            for (int i=0;i<sibling_cnt;i++){
                q.offer(now_node.getChildAt(i));
            }

            q.poll();
        }
    }

    public int getTreeHeight(treeIO node){
        Queue q=new LinkedList();

        node.setTree_depth(1);
        q.offer(node);
        int MAX=-1234567890;

        while(q.peek()!=null){
            if (node.getTree_depth()>MAX)
                MAX=node.getTree_depth();

            for (int i=0;i<node.getChildCount();i++){
                q.offer(node.getChildAt(i));
                node.getChildAt(i).setTree_depth(node.getTree_depth()+1);
            }
            node=(IO.treeIO)q.peek();
            q.poll();
        }

        return MAX;
    }
}
