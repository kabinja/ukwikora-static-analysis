package org.ukwikora.staticanalysis.model;

import javax.persistence.*;

@Entity
@Table(name = "statements")
public class StatementEntity extends AbstractEntity{
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "start_line", nullable = false)
    private int startLine;
    @Column(name = "end_line", nullable = false)
    private int endLine;
    @Column(name = "file", nullable = false)
    private String file;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
