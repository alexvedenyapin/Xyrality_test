package com.alex.xyrality_test.xyrality_test.rest.requests;

public class GetWorldsRequest {

    private String login;
    private String password;
    private String deviceType;
    private String deviceId;

    public GetWorldsRequest(String login, String password, String deviceType, String deviceId) {
        this.login = login;
        this.password = password;
        this.deviceType = deviceType;
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "GetWorlds{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
