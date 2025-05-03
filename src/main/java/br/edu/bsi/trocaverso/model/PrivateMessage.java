package br.edu.bsi.trocaverso.model;

import java.time.LocalDate;

public class PrivateMessage extends GenericModel{
    private User sender;
    private User receiver;
    private String content;
    private LocalDate date;
}
