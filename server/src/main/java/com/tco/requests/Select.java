package com.tco.requests;

public class Select {
    static String match(String match, int limit, String columns, String table) {
       return statement(match, "DISTINCT " + columns, "LIMIT " + limit, table);
   }

    static String found(String match, String table) {
       return statement(match, "COUNT(*) AS count ", "", table);
   }

    static String statement(String match, String data, String limit, String table) {
        limit = checkLimit(limit);

       return "SELECT "
           + data
           + " FROM " + table
           + " INNER JOIN country ON world.iso_country = country.id"
           + " INNER JOIN region ON world.iso_region = region.id"
           + " WHERE (world.id LIKE \"%" + match + "%\" "
           + " OR world.name LIKE \"%" + match + "%\" "
           + " OR world.municipality LIKE \"%" + match + "%\" "
           + " OR region.name LIKE \"%" + match + "%\" "
           + " OR country.name LIKE \"%" + match + "%\") "
           + limit
           + " ;";
   }

   static String checkLimit(String limit){
        String newlimit = limit;
        if(limit.equals("LIMIT 0")){
            newlimit = "LIMIT 125";
        }
        return newlimit;
   }

}
