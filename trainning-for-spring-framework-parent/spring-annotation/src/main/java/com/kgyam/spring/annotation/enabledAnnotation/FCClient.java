package com.kgyam.spring.annotation.enabledAnnotation;

/**
 * @author kg yam
 * @date 2021-05-06 12:00
 * @since
 */
public class FCClient {
    private String host = "127.0.0.1";
    private int port = 8080;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "FCClient{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
