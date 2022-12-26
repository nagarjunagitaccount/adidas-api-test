package adidas.api.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataAccessConf {
    private static DataAccessConf singleton;
    private static Properties configProperties;


    public static DataAccessConf get() {
        if (null == singleton) {
            Properties props = new Properties();

            InputStream stream = DataAccessConf.class.getResourceAsStream("/config.properties");


            try {
                props.load(stream);


            } catch (IOException e) {
                throw new RuntimeException("Unable to read properties file");
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                    }
                }
            }
            DataAccessConf conf = new DataAccessConf();
            conf.configProperties = props;
            singleton = conf;
        }
        return singleton;
    }


    public String gethost() {
        String cn = configProperties.getProperty("HOST");
        return cn;

    }


}