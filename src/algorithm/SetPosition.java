package algorithm;
import IO.treeIO;
import layout.CenterPanel;
import layout.MainLayout;
import sun.nio.cs.ext.MacThai;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SetPosition {
    ArrayList<treeIO> root_list;
    double panelWeight, panelHeight;
    public SetPosition(double panelWeight, double panelHeight){
        root_list=layout.MainLayout.getTree();
        this.panelWeight = panelWeight;
        this.panelHeight = panelHeight;
    }

    public void start_SetPosition() {
        make_grahp();
    }

    public void make_grahp(){

        for (int t=0;t<root_list.size();t++) {
            int child_cnt;
            int tree_height = getTreeHeight(root_list.get(t));
            Queue q = new LinkedList();
            treeIO now_node = root_list.get(t);

            q.offer(now_node);

            now_node.setX(panelWeight / 2);
            now_node.setY(panelHeight / 2);
            now_node.setW(50);
            now_node.setH(31);
            now_node.set_angle(0);
            double Adj_X = 0, Adj_Y = 0;

            while (!q.isEmpty()) {
                now_node = (IO.treeIO) q.peek();
                int sibling_cnt = now_node.getChildCount();

                double X, Y;
                int cnt = 0;
                X = now_node.getX();
                Y = now_node.getY();

                int sibling_num = number_of_sibiling(now_node);
                int num = now_node.get_child_number_in_parent();
                double lim_angle = ((double) 1 / (double) sibling_num) * Math.PI * 2;

                int length = 100 + sibling_cnt*20;

                int a=now_node.get_length_from_parent();
                int b=length;
                double d=now_node.get_lim_angle()/2.0;
                double limit;
                 if(a!=b) {
                     limit = 2 * 2 * (Math.PI + Math.atan((b * (1.0/Math.tan(d)) - Math.sqrt(Math.abs(-Math.pow(a, 2.0) + Math.pow(b, 2.0) + Math.pow(b, 2) * Math.pow((1.0) / d, 2.0)))) / (double) (a - b)));
                 }
                 else
                     limit=Math.PI*2/3.0;

                 while(limit>=2*Math.PI){
                     limit-=2*Math.PI;
                 }

                 System.out.println(now_node.getTree_depth()+"높이 : "+limit+" "+a+" "+b+" "+d+" ");
                if (now_node.get_child_number_in_parent()!=-1){
                    if (lim_angle>limit)
                        lim_angle = limit;
                }

                for (int i = 0; i < sibling_cnt; i++) {
                    double angle;
                    now_node.getChildAt(i).set_length_from_parent(length);
                    if (now_node.get_child_number_in_parent() == -1) {
                        angle = ((double) i / (double) sibling_cnt) * Math.PI * 2;
                        if (sibling_cnt<=2)
                            now_node.getChildAt(i).set_lim_angle(Math.PI/3.0);
                        else
                            now_node.getChildAt(i).set_lim_angle((1.0 / (double) sibling_cnt) * Math.PI * 2);
                    }
                    else {
                        if (sibling_cnt == 1) {
                            angle = now_node.get_angle();
                            now_node.getChildAt(i).set_lim_angle(now_node.get_lim_angle());
                            now_node.getChildAt(i).set_length_from_parent(length+now_node.get_length_from_parent());
                        } else {
                            angle = (now_node.get_angle() - (lim_angle / 2.0)) + (lim_angle / (double) (sibling_cnt - 1)) * i;
                            now_node.getChildAt(i).set_lim_angle((lim_angle / (double) (sibling_cnt)));
                        }
                    }

                    now_node.getChildAt(i).set_angle(angle);
                    q.offer(now_node.getChildAt(i));
                    now_node.getChildAt(i).setX(X + Math.cos(angle) * length);
                    now_node.getChildAt(i).setY(Y + Math.sin(angle) * length);
                    now_node.getChildAt(i).setW(50);
                    now_node.getChildAt(i).setH(31);

                    if (X + Math.cos(angle) * length < -Adj_X)
                        Adj_X = -(X + Math.cos(angle) * length);

                    if (Y + Math.sin(angle) * length < -Adj_Y)
                        Adj_Y = -(Y + Math.sin(angle) * length);

                }

                q.poll();
            }

            now_node = root_list.get(t);
            q.offer(now_node);

            while (!q.isEmpty()) {
                now_node = (treeIO) q.peek();
                int num = now_node.getChildCount();
                double X, Y;
                X = now_node.getX();
                Y = now_node.getY();
                now_node.setX(X + Adj_X);
                now_node.setY(Y + Adj_Y);

                for (int i = 0; i < num; i++) {
                    q.offer(now_node.getChildAt(i));
                }



                q.poll();
            }
        }
    }

    public int getTreeHeight(treeIO node){
        Queue q=new LinkedList();
        treeIO now_node;
        node.setTree_depth(1);
        q.offer(node);
        int MAX=-1234567890;

        while(q.peek()!=null){
            now_node=(treeIO)q.peek();
            if (now_node.getTree_depth()>MAX)
                MAX=now_node.getTree_depth();

            for (int i=0;i<now_node.getChildCount();i++){
                q.offer(now_node.getChildAt(i));
                now_node.getChildAt(i).setTree_depth(now_node.getTree_depth()+1);
            }
            q.poll();
        }

        return MAX;
    }

    public int number_of_sibiling(treeIO node) {
        if (node.get_child_number_in_parent() == -1)
            return -1;
        else
            return node.getParent().getChildCount();
    }

    public void set_line(){

        CenterPanel centerPanel = layout.MainLayout.getCenterPanel();
        centerPanel.clear_list();
        Queue q = new LinkedList();
        double grd,grd1,grd2;

        boolean flag1=false,flag2=false;


        for (int t=0;t<root_list.size();t++){
            ArrayList<Double>  tmp_sx=new ArrayList<Double>();
            ArrayList<Double>  tmp_sy=new ArrayList<Double>();
            ArrayList<Double>  tmp_ex=new ArrayList<Double>();
            ArrayList<Double>  tmp_ey=new ArrayList<Double>();
            treeIO now_node=root_list.get(t);
            q.offer(now_node);
            while(!q.isEmpty()){
                now_node=(treeIO)q.peek();
                int child_cnt=now_node.getChildCount();
                double X,Y;
                double W,H;
                double child_X,child_Y;
                double child_W,child_H;
                double sx,sy,ex,ey;

                X=now_node.getX();
                Y=now_node.getY();
                W=now_node.getW();
                H=now_node.getH();
                now_node.clear_exit();

                for (int i=0;i<child_cnt;i++){
                    q.offer(now_node.getChildAt(i));
                    child_X=now_node.getChildAt(i).getX();
                    child_Y=now_node.getChildAt(i).getY();
                    child_W=now_node.getChildAt(i).getW();
                    child_H=now_node.getChildAt(i).getH();
                    if (child_X-X!=0){
                        grd=((child_Y+child_H/2.0)-(Y+H/2.0))/((child_X+child_W/2.0)-(X+W/2.0));
                        if ((grd>=H/W || grd<=-H/W) && Y>child_Y){
                            now_node.set_exit(X+(W/2.0),Y);
                            now_node.getChildAt(i).set_enter(child_X+(child_W/2.0),child_Y+child_H);
                            sx=X+(W/2.0);
                            sy=Y;
                            ex=child_X+(child_W/2.0);
                            ey=child_Y+child_H;
                        }
                        else if (grd>-H/W && grd<H/W && child_X>X){
                            now_node.set_exit(X+W,Y+H/2.0);
                            now_node.getChildAt(i).set_enter(child_X, child_Y+child_H/2.0);
                            sx=X+W;
                            sy=Y+H/2.0;
                            ex=child_X;
                            ey=child_Y+child_H/2.0;
                        }
                        else if ((grd>=H/W || grd<=-H/W) && Y<child_Y){
                            now_node.set_exit(X+(W/2.0),Y+H);
                            now_node.getChildAt(i).set_enter(child_X+(child_W/2.0),child_Y);
                            sx=X+(W/2.0);
                            sy=Y+H;
                            ex=child_X+(child_W/2.0);
                            ey=child_Y;
                        }
                        else {
                            now_node.set_exit(X, Y + H / 2.0);
                            now_node.getChildAt(i).set_enter(child_X + child_W, child_Y + child_H / 2.0);
                            sx=X;
                            sy=Y+H/2.0;
                            ex=child_X+child_W;
                            ey=child_Y+child_H/2.0;
                        }

                    }
                    else if (child_Y>Y){
                        now_node.set_exit(X+(W/2.0),Y+H);
                        now_node.getChildAt(i).set_enter(child_X+(child_W/2.0),child_Y);
                        sx=X+(W/2.0);
                        sy=Y+H;
                        ex=child_X+(child_W/2.0);
                        ey=child_Y;
                    }
                    else{
                        now_node.set_exit(X+(W/2.0),Y);
                        now_node.getChildAt(i).set_enter(child_X+(child_W/2.0),child_Y+child_H);
                        sx=X+(W/2.0);
                        sy=Y;
                        ex=child_X+(child_W/2.0);
                        ey=child_Y+H;
                    }

                    tmp_sx.add(sx);
                    tmp_sy.add(sy);
                    tmp_ex.add(ex);
                    tmp_ey.add(ey);
                }
                q.poll();

            }

            centerPanel.start_x.add(tmp_sx);

            centerPanel.start_y.add(tmp_sy);
            centerPanel.end_x.add(tmp_ex);
            centerPanel.end_y.add(tmp_ey);


        }
    }

}
