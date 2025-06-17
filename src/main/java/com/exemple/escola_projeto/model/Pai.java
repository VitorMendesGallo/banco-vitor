package com.exemple.escola_projeto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pais")
public class Pai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPai;

    @NotNull(message = "O nome do Pai é obrigatório")
    private String nomePai;

    @NotNull(message = "A data de nascimento do Pai é obrigatória")
    private String nascimentoPai;

    @NotNull(message = "O endereço do Pai é obrigatório")
    private String enderecoPai;

    @NotNull(message = "O número da casa do Pai é obrigatório")
    private String numeroCasaPai;

    @NotNull(message = "O CEP do Pai é obrigatório")
    private String cepPai;

    @NotNull(message = "O CPF do Pai é obrigatório")
    private String cpfPai;

    @NotNull(message = "O RG do Pai é obrigatório")
    private String rgPai;

    @NotNull(message = "A profissão do Pai é obrigatória")
    private String profissaoPai;

    @NotNull(message = "O telefone do Pai é obrigatório")
    private String telefonePai;

    @NotNull(message = "O e-mail do Pai é obrigatório")
    private String emailPai;

    @NotNull(message = "O local de trabalho do Pai é obrigatório")
    private String trabalhoPai;

    @NotNull(message = "O telefone de trabalho do Pai é obrigatório")
    private String telefoneTrabalhoPai;

    public Long getIdPai() {
        return idPai;
    }

    public void setIdPai(Long idPai) {
        this.idPai = idPai;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNascimentoPai() {
        return nascimentoPai;
    }

    public void setNascimentoPai(String nascimentoPai) {
        this.nascimentoPai = nascimentoPai;
    }

    public String getEnderecoPai() {
        return enderecoPai;
    }

    public void setEnderecoPai(String enderecoPai) {
        this.enderecoPai = enderecoPai;
    }

    public String getNumeroCasaPai() {
        return numeroCasaPai;
    }

    public void setNumeroCasaPai(String numeroCasaPai) {
        this.numeroCasaPai = numeroCasaPai;
    }

    public String getCepPai() {
        return cepPai;
    }

    public void setCepPai(String cepPai) {
        this.cepPai = cepPai;
    }

    public String getCpfPai() {
        return cpfPai;
    }

    public void setCpfPai(String cpfPai) {
        this.cpfPai = cpfPai;
    }

    public String getRgPai() {
        return rgPai;
    }

    public void setRgPai(String rgPai) {
        this.rgPai = rgPai;
    }

    public String getProfissaoPai() {
        return profissaoPai;
    }

    public void setProfissaoPai(String profissaoPai) {
        this.profissaoPai = profissaoPai;
    }

    public String getTelefonePai() {
        return telefonePai;
    }

    public void setTelefonePai(String telefonePai) {
        this.telefonePai = telefonePai;
    }

    public String getEmailPai() {
        return emailPai;
    }

    public void setEmailPai(String emailPai) {
        this.emailPai = emailPai;
    }

    public String getTrabalhoPai() {
        return trabalhoPai;
    }

    public void setTrabalhoPai(String trabalhoPai) {
        this.trabalhoPai = trabalhoPai;
    }

    public String getTelefoneTrabalhoPai() {
        return telefoneTrabalhoPai;
    }

    public void setTelefoneTrabalhoPai(String telefoneTrabalhoPai) {
        this.telefoneTrabalhoPai = telefoneTrabalhoPai;
    }

}
