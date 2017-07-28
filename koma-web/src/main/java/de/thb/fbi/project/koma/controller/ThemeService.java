package de.thb.fbi.project.koma.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.thb.fbi.project.koma.data.Kompetenz;
import de.thb.fbi.project.koma.util.KompetenzProducer;

@Named
@ApplicationScoped
public class ThemeService {
     
	@Inject KompetenzProducer kompetenzProducer;
    private List<Kompetenz> themes;
     
    @PostConstruct
    public void init() {
        themes = new ArrayList<Kompetenz>();
        themes = kompetenzProducer.getKompetenzen();
       
    }
     
    public List<Kompetenz> getThemes() {
        return themes;
    } 
}