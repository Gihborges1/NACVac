package com.example.vacina;

public class Paciente {
    private Integer id;
    private String vacina;
    private String data_vac;
    private String unidade;
    private String dose;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getVacina(){
        return vacina;
    }

    public void setVacina(String vacina){
        this.vacina = vacina;
    }

    public String getData_vac(){
        return data_vac;
    }

    public void setData_vac(String data_vac){
        this.data_vac = data_vac;
    }

    public String getUnidade(){
        return unidade;
    }

    public void setUnidade(String unidade){
        this.unidade = unidade;
    }

    public String getDose(){
        return dose;
    }

    public void setDose(String dose){
        this.dose = dose;
    }
}
