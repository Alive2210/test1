package entityData;

import java.util.Objects;

public class FieldsBean implements java.io.Serializable {
    private String login;
    private String pass;
    private String ip;
    private String port;
    private String nameDb;
    private String driverName;
    private long n;


    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getNameDb() {
        return nameDb;
    }

    public void setNameDb(String nameDb) {
        this.nameDb = nameDb;
    }

    public long getN() {
        return n;
    }

    public void setN(long n) {
        this.n = n;
    }


    @Override
    public int hashCode() {
        return Objects.hash(nameDb,port,ip,login,pass,driverName,n);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        FieldsBean fieldsBean = (FieldsBean) obj;
        return n == fieldsBean.n
                &&  (nameDb != null && nameDb.equals(fieldsBean.getNameDb()))
                &&  (port != null && port.equals(fieldsBean.getNameDb()))
                &&  (ip != null && ip.equals(fieldsBean.getNameDb()))
                &&  (login != null && login.equals(fieldsBean.getNameDb()))
                &&  (driverName != null && driverName.equals(fieldsBean.getDriverName()))
                &&  (pass != null && pass.equals(fieldsBean.getNameDb()));
    }

    @Override
    public String toString() {
        return "entityData.FieldsBean{" +
                "nameDb = " + nameDb +
                "port = "   + port +
                "ip = "     + ip +
                "login = "  + login +
                "pass = "   + pass +
                "driverName = "   + driverName +
                "n = "      + String.valueOf(n) +
                "}";
    }

}
