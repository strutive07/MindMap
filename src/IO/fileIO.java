package IO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class fileIO {
    public static void export_Tree(ArrayList<treeIO> root_list){
        Gson gson = new Gson();
        for(int i=0; i<root_list.size(); i++){
            JsonObject jsonObject = create_Json(root_list.get(i));
            System.out.println(jsonObject.toString());
        }
    }
    public static void import_Json(){

    }

    private static JsonObject create_Json(treeIO node){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", node.getStringName());
        jsonObject.addProperty("w", node.getW());
        jsonObject.addProperty("h", node.getH());
        jsonObject.addProperty("x", node.getX());
        jsonObject.addProperty("y", node.getY());
        JsonArray jsonArray = new JsonArray();

        for(int i=0; i<node.getChildCount(); i++){
            if(node.getChildAt(i) != null){
                JsonObject next_node = create_Json(node.getChildAt(i));
                jsonArray.add(next_node.toString());
            }
        }
        jsonObject.add("child", jsonArray);
        return jsonObject;
    }

//    private static treeIO create_Tree(String jsonString){
//
//    }
}