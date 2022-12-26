package api.resources;

public class resources {
    public static String getallpetsbystatus()
    {
        String res="/v2/pet/findByStatus";
        return res;

    }
    public static String getcreatepet()
    {
        String res="/v2/pet";
        return res;
    }
    public  static String getpetbyid(){
        String res="/v2/pet/{id}";
        return res;
    }

}
