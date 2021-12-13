package com.kinocms.cinema.сontrollers.admin;

import com.kinocms.cinema.model.*;
import com.kinocms.cinema.services.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultEntriesInDataBase implements ApplicationRunner {
    private static Logger logger = LogManager.getLogger(DefaultEntriesInDataBase.class.getName());

    @Autowired
    private MainPagesInformationService mainPagesInformationService;
    @Autowired
    private DescriptionCurrentCinemasService descriptionCurrentCinemasService;
    @Autowired
    private CafesService cafesService;
    @Autowired
    private VipHallsService vipHallsService;
    @Autowired
    private AdvertisementsService advertisementsService;
    @Autowired
    private ChildrensRoomsService childrensRoomsService;
    @Autowired
    private ContactsService contactsService;
    @Autowired
    private LanguagesService languagesService;
    @Autowired
    private GendersService gendersService;
    @Autowired
    private CitiesService citiesService;

    @Override
    public void run(ApplicationArguments args) {
        try {
            logger.info("create default entries");
            if (mainPagesInformationService.findAll().isEmpty()) {
                mainPagesInformationService.save(new MainPagesInformationEntity());
            }

            if (descriptionCurrentCinemasService.findAll().isEmpty()) {
                descriptionCurrentCinemasService.save(new DescriptionCurrentCinemasEntity());
            }

            if (cafesService.findAll().isEmpty()) {
                cafesService.save(new CafesEntity());
            }

            if (vipHallsService.findAll().isEmpty()) {
                vipHallsService.save(new VipHallsEntity());
            }

            if (advertisementsService.findAll().isEmpty()) {
                advertisementsService.save(new AdvertisementsEntity());
            }

            if (childrensRoomsService.findAll().isEmpty()) {
                childrensRoomsService.save(new ChildrensRoomsEntity());
            }

            if (contactsService.findAll().isEmpty()) {
                contactsService.save(new ContactsEntity());
            }

            if (languagesService.findAll().isEmpty()) {
                languagesService.save(new LanguagesEntity("Русский"));
                languagesService.save(new LanguagesEntity("Украинский"));
            }

            if (gendersService.findAll().isEmpty()) {
                gendersService.save(new GendersEntity("Мужской"));
                gendersService.save(new GendersEntity("Женский"));
            }

            if (citiesService.findAll().isEmpty()) {
                citiesService.save(new CitiesEntity("Одесса"));
                citiesService.save(new CitiesEntity("Киев"));
                citiesService.save(new CitiesEntity("Харьков"));
            }
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            exception.fillInStackTrace();
        }
    }
}
