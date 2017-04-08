package com.rohit.garorasu.productrxtask;

/**
 * Created by garorasu on 8/4/17.
 */




import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Schema {

    private String field;
    private String type;
    private String collation;
    private String _null;
    private String key;
    private String _default;
    private String extra;
    private String privileges;
    private String comment;


    public Schema() {
    }

    /**
     *
     * @param field
     * @param extra
     * @param _default
     * @param privileges
     * @param _null
     * @param comment
     * @param type
     * @param key
     * @param collation
     */
    public Schema(String field, String type, String collation, String _null, String key, String _default, String extra, String privileges, String comment) {
        super();
        this.field = field;
        this.type = type;
        this.collation = collation;
        this._null = _null;
        this.key = key;
        this._default = _default;
        this.extra = extra;
        this.privileges = privileges;
        this.comment = comment;
    }

    public String getField() {
        return field;
    }

    public String getType() {
        return type;
    }

    public String get_type(){
            if(type.contains("varchar")) {
                return "String";
            }
            if(type.contains("int")){
                return "int";
            }
            return "";
    }
    public int size(){
        String answer = type.substring(type.indexOf("(")+1,type.indexOf(")"));
        return Integer.parseInt(answer);
    }

    public String getCollation() {
        return collation;
    }

    public String getNull() {
        return _null;
    }

    public String getKey() {
        return key;
    }

    public String getDefault() {
        return _default;
    }

    public String getExtra() {
        return extra;
    }

    public String getPrivileges() {
        return privileges;
    }


    public String getComment() {
        return comment;
    }

    public static Schema jsonToSchema(JSONObject jsonObject){

        try {
            String field = jsonObject.getString("Field");
            String type = jsonObject.getString("Type");
            String collation = jsonObject.getString("Collation");
            String _null = jsonObject.getString("Null");
            String key = jsonObject.getString("Key");
            String _default = jsonObject.getString("Default");
            String extra = jsonObject.getString("Extra");
            String privileges = jsonObject.getString("Privileges");
            String comment = jsonObject.getString("Comment");
            return new Schema(field,type,collation,_null,key,_default,extra,privileges,comment);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
