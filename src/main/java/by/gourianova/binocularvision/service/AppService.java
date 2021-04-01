package by.gourianova.binocularvision.service;

import by.gourianova.binocularvision.bean.App;

import java.util.ArrayList;

public interface AppService {
    ArrayList<App> findAll();

    void createApp(App app);
}
