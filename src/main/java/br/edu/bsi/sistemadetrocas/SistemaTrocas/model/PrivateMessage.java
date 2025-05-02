package br.edu.bsi.sistemadetrocas.SistemaTrocas.model;

import java.time.LocalDate;

public class PrivateMessage extends GenericModel{
    private User sender;
    private User receiver;
    private String content;
    private LocalDate date;
}
