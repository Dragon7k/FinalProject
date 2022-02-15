package by.epam.gamestore.controller.command;

public class CommandResponse {
    private String response;
    private int status;

    public CommandResponse(String response, int status) {
        this.response = response;
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public int getStatus() {
        return status;
    }
}
