package by.epam.gamestore.controller.command;

public class CommandResponse {
    private String json;
    private String keyWord;

    public CommandResponse(String json, String keyWord) {
        this.json = json;
        this.keyWord = keyWord;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
