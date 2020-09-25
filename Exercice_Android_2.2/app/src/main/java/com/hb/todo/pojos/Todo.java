package com.hb.todo.pojos;

public class Todo {

    // attributs
    private long id;
    private String name;
    private String urgency;

    // constructeurs
    public Todo() {
    }

    public Todo(String name, String urgency) {
        this.name = name;
        this.urgency = urgency;
    }

    public Todo(long id, String name, String urgency) {
        this.id = id;
        this.name = name;
        this.urgency = urgency;
    }

    // getters & setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    // toString
    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", urgency='" + urgency + '\'' +
                '}';
    }
}
