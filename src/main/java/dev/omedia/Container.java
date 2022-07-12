package dev.omedia;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Container {
    @JsonProperty("container_name")
    private String name;

    @JsonProperty("build")
    private String build;

    @JsonProperty("volumes")
    private List<String> volumes;

    public Container() {
    }

    public Container(String name, String build, List<String> volumes) {
        this.name = name;
        this.build = build;
        this.volumes = volumes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public List<String> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<String> volumes) {
        this.volumes = volumes;
    }

    @Override
    public String toString() {
        return "Container{" +
                "name='" + name + '\'' +
                ", build='" + build + '\'' +
                ", volumes=" + volumes +
                '}';
    }
}
