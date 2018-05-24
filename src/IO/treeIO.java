package IO;

import javax.swing.tree.TreeNode;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;

public class treeIO{
    private String name;
    private int null_cnt = 0;
    private int child_number_in_parent = -1;
    private treeIO parent = null;
    ArrayList<treeIO> children = new ArrayList<treeIO>();

    public treeIO(String name){
        this.name = name;
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
}
