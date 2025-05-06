package br.edu.bsi.trocaverso.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_private_message")
public class PrivateMessage extends GenericModel{

    @OneToOne
    @JoinColumn(name = "message_sender", nullable = false)
    private User sender;

    @OneToOne
    @JoinColumn(name = "message_receiver", nullable = false)
    private User receiver;

    @Column(name = "message_content", nullable = false)
    private String content;

    @Column(name = "message_date", nullable = false)
    private LocalDate date;

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
