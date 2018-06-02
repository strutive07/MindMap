
package IO;

        import com.google.gson.*;
        import com.google.gson.reflect.TypeToken;
        import com.google.gson.stream.JsonReader;
        import jdk.nashorn.internal.parser.JSONParser;

        import java.io.*;
        import java.lang.reflect.Type;
        import java.nio.charset.Charset;
        import java.nio.file.Files;
        import java.nio.file.Paths;
        import java.util.ArrayList;
        import java.util.List;

public class fileIO {
    public static void OutputJson(String jsonObject){
        try{
            String pretty_string = toPrettyFormat(jsonObject);
            FileWriter writer = new FileWriter("OutPut.json");
            writer.write(pretty_string);

            writer.close();
        }catch (Exception e){
            System.out.println("TAG:JSON_OUTPUT_ERROR:"+e);
        }
    }
    public static void export_Tree(ArrayList<treeIO> root_list){
        Gson gson = new Gson();
        for(int i=0; i<root_list.size(); i++){
            JsonObject jsonObject = create_Json(root_list.get(i));
            OutputJson(jsonObject.toString());
        }
    }

    public static void import_Json(String filePath){

        try {
            String jsonString = readFile(filePath, Charset.defaultCharset());
            ArrayList<treeIO> root_list = layout.MainLayout.getTree();
            root_list.clear();

            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(jsonString);
            JsonObject root_object = jsonElement.getAsJsonObject();

            treeIO root_node = new treeIO(root_object.get("name").getAsString());
            root_node.setX(root_object.get("x").getAsDouble());
            root_node.setY(root_object.get("y").getAsDouble());
            root_node.setH(root_object.get("h").getAsDouble());
            root_node.setW(root_object.get("w").getAsDouble());
            root_node.setLabelColor(root_object.get("labelColor").getAsInt());
            JsonArray jsonArray = root_object.get("child").getAsJsonArray();
            for(int i=0; i<jsonArray.size(); i++){
                root_node.push_child(create_Tree(jsonArray.get(i)));
            }
            root_list.add(root_node);
            dfs(root_node);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JsonObject create_Json(treeIO node){


        JsonObject jsonObject = new JsonObject();


        jsonObject.addProperty("name", node.getStringName());
        jsonObject.addProperty("w", node.getW());
        jsonObject.addProperty("h", node.getH());
        jsonObject.addProperty("x", node.getX());
        jsonObject.addProperty("y", node.getY());
        jsonObject.addProperty("labelColor", node.getLabelColor());
        JsonArray jsonArray = new JsonArray();

        for(int i=0; i<node.getChildCount(); i++){
            if(node.getChildAt(i) != null){
                JsonObject next_node = create_Json(node.getChildAt(i));
                jsonArray.add(next_node);
            }
        }
        jsonObject.add("child", jsonArray);
        return jsonObject;
    }

    private static treeIO create_Tree(JsonElement element){

        JsonObject root_object = element.getAsJsonObject();

        treeIO node = new treeIO(root_object.get("name").getAsString());
        node.setX(root_object.get("x").getAsDouble());
        node.setY(root_object.get("y").getAsDouble());
        node.setH(root_object.get("h").getAsDouble());
        node.setW(root_object.get("w").getAsDouble());
        node.setLabelColor(root_object.get("labelColor").getAsInt());

        JsonArray jsonArray = root_object.get("child").getAsJsonArray();
        for(int i=0; i<jsonArray.size(); i++){
            node.push_child(create_Tree(jsonArray.get(i)));
        }
        return node;
    }
    public static String toPrettyFormat(String jsonString)
    {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);

        return prettyJson;
    }
    private static void dfs(treeIO node){
        if(node.get_child_number_in_parent() == -1){
            System.out.println("Root 입니다. : " + node.getStringName());
        }else{
            System.out.println("부모 : " + node.getParent().getStringName() + " : " + node.getStringName());
        }
        for (int i=0; i<node.getChildCount(); i++){
            treeIO next_node = node.getChildAt(i);
            if(next_node != null){
                dfs(next_node);
            }
        }
    }
    static String readFile(String path, Charset encoding)
    throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}