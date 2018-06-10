package IO;

import javax.swing.tree.TreeNode;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;

public class treeIO{
    private String name;
    private double w, h;
    private double x, y;
    private int Tree_depth;
    private int LabelColor;
    private double angle;
    private double lim_angle;
    private int length_from_parent;
    private int nodeNumber;
    private double enter_x,enter_y;
    private ArrayList<Double> exit_x;
    private ArrayList<Double> exit_y;

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getLabelColor() {
        return LabelColor;
    }

    public void setLabelColor(int labelColor) {
        LabelColor = labelColor;
    }

    private int null_cnt = 0;
    private int child_number_in_parent = -1;
    private treeIO parent = null;

    ArrayList<treeIO> children = new ArrayList<treeIO>();

    public treeIO(String name, int nodeNumber){
        this.name = name;
        this.LabelColor = 0xFFF7E4;
        this.nodeNumber = nodeNumber;
        this.exit_x=new ArrayList<Double>();
        this.exit_y=new ArrayList<Double>();
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public String getStringName(){
        return this.name;
    }

    public treeIO getChildAt(int childIndex) {
        if(childIndex < 0 || childIndex >= children.size()){
            return null;
        }else{
            if(children.get(childIndex) == null){
                return null;
            }else{
                return children.get(childIndex);
            }
        }
    }

    public void set_child_number_in_parent(int child_number_in_parent){
        this.child_number_in_parent = child_number_in_parent;
    }

    public int get_child_number_in_parent(){
        return child_number_in_parent;
    }

    public int getChildCount() {
        return children.size();
    }


    public void push_child(treeIO child){
        child.setParent(this);
        children.add(child);
        child.set_child_number_in_parent(children.size() - 1);
    }

    public void delete_child(treeIO child){
        parent.children.set(child_number_in_parent, null);
        null_cnt++;
    }


    public treeIO getParent() {
        return this.parent;
    }

    public treeIO setParent(treeIO parent) {
        return this.parent = parent;
    }



    public boolean isLeaf() {
        if(children.size() - null_cnt == 0){
            return true;
        }else{
            return false;
        }
    }

    public int getTree_depth(){
        return Tree_depth;
    }

    public void setTree_depth(int n){
        Tree_depth=n;
    }

    public void set_angle(double x){
        angle=x;
    }

    public double get_angle(){
        return angle;
    }

    public void set_lim_angle(double x){
        lim_angle=x;
    }

    public double get_lim_angle(){
        return lim_angle;
    }
    public void set_length_from_parent(int x){
        length_from_parent=x;
    }

    public int get_length_from_parent(){
        return length_from_parent;
    }


    public void set_enter(double x,double y){
        enter_x=x;
        enter_y=y;
    }

    public void set_exit(double x,double y){
        exit_x.add(x);
        exit_y.add(y);
    }

    public void clear_exit(){
        exit_x.clear();
        exit_y.clear();
    }
}
