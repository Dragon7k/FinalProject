package by.epam.gamestore.entity;

public class Game extends AbstractEntity {
    private String name;
    private double price;
    private String description;
    private String developer;

    public Game() {
    }

    public Game(long id, String name, double price, String description, String developer) {
        super(id);
        this.name = name;
        this.price = price;
        this.description = description;
        this.developer = developer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Game{");
        sb.append("name='").append(name).append('\'');
        sb.append(", price='").append(price).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", developer='").append(developer).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class GameBuilder {
        private long id;
        private String name;
        private double price;
        private String description;
        private String developer;

        public GameBuilder() {
        }

        public GameBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public GameBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public GameBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public GameBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public GameBuilder setDeveloper(String developer) {
            this.developer = developer;
            return this;
        }

        public Game buildGame() {
            return new Game(id, name, price, description, developer);
        }
    }
}
