package ctwhs.order.dto;

/**
 * Created by UNGTQ on 6/4/2017.
 */

public class ServerInfoDto {
    /**
     * server name info
     */
    private String serverName;

    /**
     * port number to communicate with server
     */
    private String portNo;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getPortNo() {
        return portNo;
    }

    public void setPortNo(String portNo) {
        this.portNo = portNo;
    }
}
