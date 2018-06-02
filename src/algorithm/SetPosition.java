package algorithm;
import IO.treeIO;
import layout.MainLayout;
import sun.nio.cs.ext.MacThai;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SetPosition {
    ArrayList<treeIO> root_list;

    public SetPosition(){
        root_list=layout.MainLayout.getTree();
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

        now_node.setX(1280/2);
        now_node.setY(640/2);

        while (!q.isEmpty()){
            now_node=(IO.treeIO)q.peek();
            int sibling_cnt=now_node.getChildCount();

            double X,Y;
            int length=10;//여기 뭐 들어가야할지 그려보면서 실험
            int cnt=0;
            X=now_node.getX();
            Y=now_node.getY();

            int sibling_num=number_of_sibiling(now_node);
            int num=now_node.get_child_number_in_parent();
            double lim_angle=(num/sibling_num)*Math.PI;

            for (int i=0;i<sibling_cnt;i++){

                if (now_node.getChildAt(i)==null)
                    continue;
                else
                    cnt++;

                double angle;

                if (sibling_cnt==1)
                    angle=lim_angle;
                else
                    angle=(lim_angle/2)+(lim_angle/(sibling_cnt-1))*i;

                q.offer(now_node.getChildAt(i));
                now_node.getChildAt(i).setX(X+Math.cos(angle)*length);
                now_node.getChildAt(i).setY(Y+Math.sin(angle)*length);
                now_node.getChildAt(i).setW(1);
                now_node.getChildAt(i).setH(1);//여기 있는 것들은 차후 width,height값에 따라 변경
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

    public int number_of_sibiling(treeIO node){
        if (node.get_child_number_in_parent()==-1)
            return -1;
        else
            return node.getParent().getChildCount();
    }
}
