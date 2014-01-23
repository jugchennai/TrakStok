package org.javamoney.trakstok.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.agorava.api.oauth.application.OAuthAppSettings;
import org.agorava.api.oauth.application.OAuthApplication;
import org.agorava.empireavenue.EmpireAvenue;

public class SettingsProducer {
    
    @ApplicationScoped
    @Produces
    @EmpireAvenue
    @OAuthApplication
    public OAuthAppSettings produceSettings;

}
