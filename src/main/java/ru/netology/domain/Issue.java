package ru.netology.domain;

public class Issue implements Comparable<Issue> {
    int id;
    int numberOfComments;
    boolean isClosed;
    String author;
    String label;
    String assignee;

    public Issue(int id, int numberOfComments, boolean isClosed, String author, String label, String assignee) {
        this.id = id;
        this.numberOfComments = numberOfComments;
        this.isClosed = isClosed;
        this.author = author;
        this.label = label;
        this.assignee = assignee;
    }

    public boolean matchesAuthor(String author) {
        return (this.getAuthor().equalsIgnoreCase(author));
    }

    public boolean matchesLabel(String label) {
        return (this.getLabel().equalsIgnoreCase(label));
    }

    public boolean matchesAssignee(String assignee) {
        return (this.getAssignee().equalsIgnoreCase(assignee));
    }

    public int getId() {
        return id;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public String getAuthor() {
        return author;
    }

    public String getLabel() {
        return label;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setStatus(boolean status) {
        isClosed = status;
    }

    @Override
    public int compareTo(Issue o) {
        return o.getNumberOfComments() - this.getNumberOfComments();
    }
}